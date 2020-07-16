package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2468_안전영역 {
	static int n, map[][], max;
	public static void main(String[] args) throws Exception{
		input();
		int rtv = solve();
		System.out.println(rtv);
	}

	private static int solve() {
		max = 1;
		int end;
		for(int i=1; i<=100; i++) {
			max = Math.max(max, end = rain(i));
			if(end==0) break;
		}
		return max;
	}

	private static int rain(int height) {
		int count = 0;
		boolean visited[][] = new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(visited[i][j] || map[i][j]<=height) continue;
				bfs(i, j, height, visited);
				count++;
			}
		}
		return count;
	}
	static int[] dy= {-1, 0, 1, 0}, dx= {0, 1, 0, -1};
	private static void bfs(int y, int x, int height, boolean[][] visited) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {y, x});
		visited[y][x]=true;
		
		int ny, nx, temp[];
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int d=0; d<4; d++) {
				ny = temp[0] + dy[d];
				nx = temp[1] + dx[d];
				if(ny<0||nx<0||ny>=n||nx>=n||visited[ny][nx]||map[ny][nx]<=height) continue;
				q.offer(new int[] {ny, nx});
				visited[ny][nx] = true;
			}
		}
	}


	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
