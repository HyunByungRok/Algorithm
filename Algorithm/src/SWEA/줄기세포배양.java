package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 줄기세포배양 {
	static int N, M, K, map[][], H, W;
	static int y, x;
	static PriorityQueue<int[]> pq;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
		int testcase = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=testcase; tc++) {
			pq.clear();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
			H = N + 2*K; W = M+2*K;
			map = new int[H][W];
			for(int i=K; i<N+K; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=K; j<M+K; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]!=0) pq.offer(new int[] {i, j, map[i][j]});
				}
			}
			sb.append("#").append(tc).append(" ").append(bfs()).append("\n");
		}
		System.out.println(sb.toString());
	}
	static int[] dy= {-1, 0, 1, 0}, dx= {0, 1, 0, -1};
	private static int bfs() {
		int temp[], ny, nx, time=0;
		PriorityQueue<int[]> readyQ = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[2]-o1[2];
			}
		});
		for(int i=0; i<K; i++) {
			while(!pq.isEmpty() && pq.peek()[2]<=time) {
				temp = pq.poll();
				if(temp[0]==-1) continue;
				pq.offer(new int[] {-1, temp[1], time+map[temp[0]][temp[1]]-1});
				for(int d=0; d<4; d++) {
					ny = temp[0]+dy[d];
					nx = temp[1]+dx[d];
					if(map[ny][nx]!=0) continue;
					readyQ.offer(new int[] {ny, nx, map[temp[0]][temp[1]]});
				}
			}
			time++;
			while(!readyQ.isEmpty()) {
				temp = readyQ.poll();
				if(map[temp[0]][temp[1]]!=0) continue;
				map[temp[0]][temp[1]]=temp[2];
				pq.offer(new int[] {temp[0], temp[1], time+temp[2]});
			}
			
		}
		return pq.size();
		
	}
}
