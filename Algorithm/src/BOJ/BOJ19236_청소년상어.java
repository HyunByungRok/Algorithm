package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ19236_청소년상어 {
	static final int SIZE = 4;
	static int map[][], shark[], fishes[][], total, max = Integer.MIN_VALUE;
	static boolean dead[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[SIZE][SIZE];
		fishes = new int[SIZE*SIZE+1][3]; // {y, x, dir}
		dead = new boolean[SIZE*SIZE+1];
		for(int i=0; i<SIZE; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<SIZE; j++) {
				int fishNum = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[i][j] = fishNum;
				fishes[fishNum] = new int[] {i, j, dir};
			}
		}
		
		//상어가 등장
		int fish = map[0][0];
		total += fish;
		dead[fish] = true;
		
		//로직 시작 parameter - {상어의 y위치, 상어의 x위치, 상어의 방향}
		solve(0, 0, fishes[fish][2]);
		System.out.println(max);
	}
	static int[] dy= {0, -1, -1, 0, 1, 1, 1, 0, -1}, dx= {0, 0, -1, -1, -1, 0, 1, 1, 1};
	private static void solve(int sy, int sx, int sd) {
		max = Math.max(max, total);
		int tempMap[][] = new int[SIZE][SIZE];
		int tempFishes[][] = new int[SIZE*SIZE+1][3];
		for(int i=0; i<SIZE; i++) tempMap[i] = map[i].clone();
		for(int i=1; i<=SIZE*SIZE; i++) tempFishes[i] = fishes[i].clone();
		//물고기들이 이동하고
		for(int i=1; i<=SIZE*SIZE; i++) {
			if(dead[i]) continue;
			int y = fishes[i][0];
			int x = fishes[i][1];
			int dir = fishes[i][2];
			for(int d=0; d<8; d++ ) {
				int nd = (dir + d > 8) ? dir + d - 8 : dir + d;
				int ny = y + dy[nd];
				int nx = x + dx[nd];
				if(isOut(ny, nx) || (ny==sy && nx==sx)) continue;
				fishes[i][2]=nd;
				swap(map[ny][nx], i);
				break;
			}
		}
		
		//상어가 다음 먹을 물고기를 먹는다.
		for(int i=1; i<=3; i++) {
			int ny = sy + dy[sd]*i;
			int nx = sx + dx[sd]*i;
			if(isOut(ny, nx) || dead[map[ny][nx]]) continue;
			int t = map[ny][nx];
			total += t;
			dead[t] = true;
			solve(ny, nx, fishes[t][2]);
			total -= t;
			dead[t] = false;
		}
		for(int i=0; i<SIZE; i++) map[i] = tempMap[i].clone();
		for(int i=1; i<=SIZE*SIZE; i++) fishes[i] = tempFishes[i].clone();
	}
	private static void swap(int num1, int num2) {
		int temp1 = map[fishes[num1][0]][fishes[num1][1]];
		map[fishes[num1][0]][fishes[num1][1]] = map[fishes[num2][0]][fishes[num2][1]];
		map[fishes[num2][0]][fishes[num2][1]] = temp1;
		temp1 = fishes[num1][0];
		fishes[num1][0] = fishes[num2][0];
		fishes[num2][0] = temp1;
		temp1 = fishes[num1][1];
		fishes[num1][1] = fishes[num2][1];
		fishes[num2][1] = temp1;
	}
	private static boolean isOut(int y, int x) {
		return y<0||x<0||y>=SIZE||x>=SIZE;
	}
}
