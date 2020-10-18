package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236_아기상어 {
	static int N, map[][];
	static class Shark{
		int y, x, weight, eaten;

		public Shark(int y, int x) {
			super();
			this.y = y;
			this.x = x;
			this.weight = 2;
			this.eaten = 0;
		}
		
	}
	static Shark shark;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					shark = new Shark(i, j);
					map[i][j] = 0;
				}
			}
		}
		
		System.out.println(solve());
	}
	static int[] dy= {-1, 0, 1, 0}, dx= {0, 1, 0, -1};
	private static int solve() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {shark.y, shark.x});
		boolean visited[][] = new boolean[N][N];
		visited[shark.y][shark.x] = true;
		int time = 0;
		List<int[]> list = new ArrayList<>();
		
		int ny, nx, count, qSize, temp[];
		count = 0;
		while(!q.isEmpty()) {
			qSize = q.size();
			count++;
			for(int qs=0; qs<qSize; qs++) {
				temp = q.poll();
				for(int d=0; d<4; d++) {
					ny = temp[0] + dy[d];
					nx = temp[1] + dx[d];
					if(isOut(ny, nx) || map[ny][nx]>shark.weight || visited[ny][nx]) continue;
					visited[ny][nx] = true;
					if(map[ny][nx]==shark.weight || map[ny][nx]==0) {
						q.offer(new int[] {ny, nx});
					}else {
						list.add(new int[] {ny, nx});
						q.offer(new int[] {ny, nx});
					}
				}
			}
			if(list.size()!=0) {
				ny=N-1; nx=N-1;
				for(int[] fish: list) {
					if(ny==fish[0] && nx > fish[1]) {
						ny = fish[0];
						nx = fish[1];
					}
					if(ny>fish[0]) {
						ny = fish[0];
						nx = fish[1];
					}
				}
				time+=count;
				q.clear();
				q.offer(new int[] {ny, nx});
				map[ny][nx]=0;
				shark.y = ny;
				shark.x = nx;
				if(++shark.eaten==shark.weight) {
					shark.weight++;
					shark.eaten=0;
				}
				list.clear();
				count=0;
				for(int i=0; i<N; i++) Arrays.fill(visited[i], false);
			}
		}
		return time;
	}
	private static boolean isOut(int ny, int nx) {
		return ny < 0 || nx < 0 || ny >= N || nx >= N;
	}
}
