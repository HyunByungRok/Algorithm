package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055_탈출 {
	static int R, C;
	static char map[][];
	static Queue<int[]> water, q;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		q = new LinkedList<>();
		water = new LinkedList<>();
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j] == '*') {
					water.offer(new int[] {i, j});
				}else if(map[i][j] == 'S') {
					q.offer(new int[] {i, j});
				}
			}
		}
		int result = solve();
		System.out.println(result == 0 ? "KAKTUS" : result);
	}
	static int[] dy= {-1, 0, 1, 0}, dx= {0, 1, 0, -1};
	private static int solve() {
		int ny, nx, dir;
		int cycle = 0;
		while(!q.isEmpty()) {
			cycle++;
			
			int size = water.size();
			for(int l=0; l<size; l++) {
				int[] temp = water.poll();
				for(int d=0; d<4; d++) {
					ny = temp[0] + dy[d];
					nx = temp[1] + dx[d];
					if(isOut(ny, nx) || map[ny][nx] == '*' || map[ny][nx] == 'X' || map[ny][nx] == 'D') continue;
					water.offer(new int[] {ny, nx});
					map[ny][nx] = '*';
				}
			}
			
			size = q.size();
			for(int l=0; l<size; l++) {
				int[] temp = q.poll();
				for(int d=0; d<4; d++) {
					ny = temp[0] + dy[d];
					nx = temp[1] + dx[d];
					if(isOut(ny, nx) || map[ny][nx] == '*' || map[ny][nx] == 'X' || map[ny][nx] == 'S') continue;
					if(map[ny][nx] == 'D') {
						return cycle;
					}
					q.offer(new int[] {ny, nx});
					map[ny][nx] = 'S';
				}
			}
		}
		
		return 0;
		
	}
	private static boolean isOut(int ny, int nx) {
		return ny < 0 || ny >= R || nx < 0 || nx >= C;
	}
}
