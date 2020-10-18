package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17142_연구소3 {
	static int N, map[][], M, picked[], total=0, min=Integer.MAX_VALUE;
	static List<int[]> virus;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		picked = new int[M];
		virus = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) virus.add(new int[] {i, j});
				else if(map[i][j]==0) total++;
			}
		}
		
		combi(0, 0);
		System.out.println(min==Integer.MAX_VALUE ? -1 : min);
	}
	private static void combi(int depth, int idx) {
		if(depth==M) {
			solve();
			return;
		}
		for(int i=idx; i<virus.size(); i++) {
			int temp[] = virus.get(i);
			picked[depth]=i;
			combi(depth+1, i+1);
		}
	}
	static int[] dy= {-1, 0, 1, 0}, dx= {0, 1, 0, -1};
	private static void solve() {
		Queue<int[]> q = new LinkedList<>();
		boolean visited[][] = new boolean[N][N];
		for(int p : picked) {
			int temp[] = virus.get(p);
			q.offer(temp);
			visited[temp[0]][temp[1]]=true;
		}
		
		int ny, nx, qSize, temp[], depth=0, count=0;
		while(!q.isEmpty()) {
			qSize = q.size();
			depth++;
			for(int qs=0; qs<qSize; qs++) {
				temp = q.poll();
				for(int d=0; d<4; d++) {
					ny = temp[0] + dy[d];
					nx = temp[1] + dx[d];
					if(isOut(ny, nx) || map[ny][nx] == 1 || visited[ny][nx]) continue;
					visited[ny][nx] = true;
					if(map[ny][nx]==0) {
						count++;
						if(count == total) {
							min = Math.min(min, depth);
							return;
						}
					}
					q.offer(new int[] {ny, nx});
				}
			}
		}
		if(count == total) {
			min = Math.min(min, 0);
			return;
		}
	}
	private static boolean isOut(int ny, int nx) {
		return ny < 0 || nx < 0 || ny >= N || nx >= N;
	}
}
