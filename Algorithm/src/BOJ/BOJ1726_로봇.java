package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1- 동, 2- 서, 3- 남, 4- 북  
 */
public class BOJ1726_로봇 {
	static int M, N, map[][], start[] = new int[3], end[] = new int[3], MIN = Integer.MAX_VALUE, visited[][][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		visited = new int[M][N][4];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				Arrays.fill(visited[i][j], Integer.MAX_VALUE);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		start[0] = Integer.parseInt(st.nextToken())-1;
		start[1] = Integer.parseInt(st.nextToken())-1;
		start[2] = Integer.parseInt(st.nextToken())-1;
		st = new StringTokenizer(br.readLine());
		end[0] = Integer.parseInt(st.nextToken())-1;
		end[1] = Integer.parseInt(st.nextToken())-1;
		end[2] = Integer.parseInt(st.nextToken())-1;
		
		dfs(start[0], start[1], start[2], 0);
		System.out.println(MIN);
	}
	static int[] dy= {0, 0, 1, -1}, dx= {1, -1, 0, 0};
	private static void dfs(int y, int x, int dir, int value) {
		if(value >= MIN || visited[y][x][dir] <= value) return;
		visited[y][x][dir] = value;
		if(y==end[0] && x==end[1] && dir==end[2]) {
			MIN = value;
			return;
		}
		
		//가던 방향으로 직진
		for(int i=1; i<=3 ; i++) {
			int ny = y + dy[dir]*i;
			int nx = x + dx[dir]*i;
			if(isOut(ny, nx) || map[ny][nx]==1) break;
			dfs(ny, nx, dir, value+1);
		}
		
		//수직 방향 전환
		if(dir<2) {
			dfs(y, x, 2, value+1);
			dfs(y, x, 3, value+1);
		}else {
			dfs(y, x, 0, value+1);
			dfs(y, x, 1, value+1);
		}
	}
	private static boolean isOut(int y, int x) {
		return y<0 || x<0 || y>=M || x>=N;
	}
}
