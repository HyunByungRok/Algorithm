package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20058_마법사상어와파이어스톰 {
	static int N, Q, map[][], size, l;
	static boolean visited[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); Q = Integer.parseInt(st.nextToken());
		size = 1<<N;
		map = new int[size][size];
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<Q; i++) {
			l = Integer.parseInt(st.nextToken());
			if(l!=0) {
				debind(0, 0, size, N);
			}
			melting();
		}
		int total = getTotal();
		visited = new boolean[size][size];
		int max = 0;
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[i][j]==0 || visited[i][j]) continue;
				max = Math.max(max, bfs(i, j));
			}
		}
		System.out.println(total);
		System.out.println(max);
	}
	private static int bfs(int y, int x) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {y, x});
		visited[y][x] = true;
		int count=1, ny, nx, temp[];
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int d=0; d<4; d++) {
				ny = temp[0] + dy[d];
				nx = temp[1] + dx[d];
				if(isOut(ny, nx) || map[ny][nx]==0 || visited[ny][nx]) continue;
				count++;
				visited[ny][nx] = true;
				q.offer(new int[] {ny, nx});
			}
		}
		return count;
	}
	private static int getTotal() {
		int sum = 0; 
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
	private static void debind(int sy, int sx, int sSize, int depth) {
		int nextSize = sSize/2;
		if(depth==0)
			return;
		debind(sy, sx, nextSize, depth-1);
		debind(sy, sx+nextSize, nextSize, depth-1);
		debind(sy+nextSize, sx+nextSize, nextSize, depth-1);
		debind(sy+nextSize, sx, nextSize, depth-1);
		if(depth<=l) {
			int temp, y = sy, x= sx;
			for(int i=0; i<nextSize; i++) {
				for(int j=0; j<nextSize; j++) {
					int ny = y+i;
					int nx = x+j;
					temp = map[ny][nx];
					for(int d=0; d<3; d++) {
						map[ny][nx] = map[ny+dy[d]*nextSize][nx+dx[d]*nextSize];
						ny+=dy[d]*nextSize;
						nx+=dx[d]*nextSize;
					}
					map[ny][nx] = temp;
				}
			}
			
		}
	}
	static int[] dy= {1, 0, -1, 0}, dx= {0, 1, 0, -1};
	private static void melting() {
		int[][] cMap = new int[size][size];
		for(int i=0; i<size; i++) cMap[i] = map[i].clone();
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(cMap[i][j]==0) continue;
				int cnt = 0;
				for(int d=0; d<4; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];
					if(isOut(ny, nx) || cMap[ny][nx]==0) continue;
					cnt++;
				}
				if(cnt>2) continue;
				map[i][j]--;
			}
		}
	}
	private static boolean isOut(int y, int x) {
		return y<0||x<0||y>=size||x>=size;
	}
}
