package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ19237_어른상어 {
	static int N, M, K, smell[][], map[][], dir[][][], count;
	static int[] dy= {0, -1, 1, 0, 0}, dx= {0, 0, 0, -1, 1};
	static class Shark {
		int y, x, dir;
		boolean dead;

		public Shark(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		public void setPos(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
	static Shark[] sharks;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = count =  Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
		smell = new int[N][N];
		map = new int[N][N];
		sharks = new Shark[M+1];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++ ) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) {
					sharks[map[i][j]] = new Shark(i, j);
					smell[i][j] = K;
				}
				
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; i++) sharks[i].dir = Integer.parseInt(st.nextToken()); 
		dir = new int[M+1][5][5];
		for(int i=1; i<=M; i++ ) {
			for(int j=1; j<=4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=1; k<=4; k++) {
					dir[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		//입력 끝
		System.out.println(solve());
	}
	private static int solve() {
		for(int t=1; t<=1001; t++) {
			//상어가 움직인 후.
			nextShark : for(int i=M; i>0; i--) {
				if(sharks[i].dead) continue;
				if(map[sharks[i].y][sharks[i].x] != i) {
					sharks[i].dead = true;
					if(--count==1) return t-1;
					continue;
				}
				for(int d=1; d<=4; d++) {
					int realD = dir[i][sharks[i].dir][d];
					int ny = sharks[i].y + dy[realD];
					int nx = sharks[i].x + dx[realD];
					if(isOut(ny, nx) || map[ny][nx]!=0) continue;
					sharks[i].setPos(ny, nx, realD);
					continue nextShark;
				}
				
				for(int d=1; d<=4; d++) {
					int realD = dir[i][sharks[i].dir][d];
					int ny = sharks[i].y + dy[realD];
					int nx = sharks[i].x + dx[realD];
					if(isOut(ny, nx) || map[ny][nx]!=i) continue;
					sharks[i].setPos(ny, nx, realD);
					break;
				}
			}
			for(int i=M; i>0; i--) {
				if(sharks[i].dead) continue;
				map[sharks[i].y][sharks[i].x] = i;
				smell[sharks[i].y][sharks[i].x] = K+1;
			}
			//냄새가 감소한다.
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(smell[i][j]==0) continue;
					if(--smell[i][j] == 0 ) map[i][j]=0;
				}
			}
		}
		return -1;
	}
	private static boolean isOut(int ny, int nx) {
		return ny < 0 || nx < 0 || ny >= N || nx>=N;
	}
}







