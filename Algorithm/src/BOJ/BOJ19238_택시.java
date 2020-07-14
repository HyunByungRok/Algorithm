package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ19238_택시 {
	static int n, m, oil, end[][], taxi[], successed, rtv, find, foundedCnt;
	static int map[][];
	static Queue<int[]> q = new LinkedList<int[]>();
	static List<int[]> list = new ArrayList<int[]>();
	static boolean visited[][];
	public static void main(String[] args) throws Exception{
		input();
		rtv = solve();
		System.out.println(rtv);
	}

	static int dy[]= {-1, 0, 1, 0}, dx[]= {0, 1, 0, -1}, ny, nx, temp[], qSize;
	private static int solve() {
		while(foundedCnt!=m) {
			rtv = findStart();
			if(rtv==-1) break;
			oil-=rtv;
			rtv = findEnd();
			if(rtv==-1) break;
			oil+=rtv;
		}
		return foundedCnt==m ?  oil : -1;
	}
	
	private static int findEnd() {
		reset();
		q.offer(new int[] {taxi[0], taxi[1]});
		visited[taxi[0]][taxi[1]]=true;
		
		int count=0;
		while(!q.isEmpty()) {
			qSize = q.size();
			count++;
			if(oil-count<0) return -1;
			for(int qs=0; qs<qSize; qs++) {
				temp = q.poll();
				for(int d=0; d<4; d++) {
					ny = temp[0] + dy[d];
					nx = temp[1] + dx[d];
					if(ny<0||nx<0||ny>=n||nx>=n||visited[ny][nx]||map[ny][nx]==-2) continue;
					if(ny == end[find][0] && nx == end[find][1]) {
						taxi[0]=ny;
						taxi[1]=nx;
						foundedCnt++;
						return count;
					}
					visited[ny][nx]=true;
					q.offer(new int[] {ny, nx});
				}
			}
		}
		return -1;
	}

	private static int findStart() {
		if(map[taxi[0]][taxi[1]]!=-1) {
			find = map[taxi[0]][taxi[1]];
			map[taxi[0]][taxi[1]]=-1;
			return 0;
		}
		reset();
		q.offer(new int[] {taxi[0], taxi[1]});
		visited[taxi[0]][taxi[1]]=true;
		
		int count=0;
		while(!q.isEmpty()) {
			qSize = q.size();
			count++;
			if(oil-count<0) return -1;
			for(int qs=0; qs<qSize; qs++) {
				temp = q.poll();
				for(int d=0; d<4; d++) {
					ny = temp[0] + dy[d];
					nx = temp[1] + dx[d];
					if(ny<0||nx<0||ny>=n||nx>=n||visited[ny][nx]||map[ny][nx]==-2) continue;
					if(0<=map[ny][nx] && map[ny][nx]<m) {
						list.add(new int[] {ny, nx});
					}
					visited[ny][nx]=true;
					q.offer(new int[] {ny, nx});
				}
			}
			if(!list.isEmpty()) {
				list.sort(new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						if(o1[0]==o2[0]) {
							return o1[1]-o2[1];
						}
						return o1[0]-o2[0];
					}
				});
				temp = list.get(0);
				find = map[temp[0]][temp[1]];
				map[temp[0]][temp[1]]=-1;
				taxi[0] = temp[0];
				taxi[1] = temp[1];
				break;
			}
		}
		return list.isEmpty() ? -1 : count;
	}

	private static void reset() {
		q.clear();
		list.clear();
		for(int i=0; i<n; i++) {
			Arrays.fill(visited[i], false);
		}
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		oil = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		visited = new boolean[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num == 0 ? -1 : -2;
			}
		}

		st = new StringTokenizer(br.readLine());
		taxi = new int[] {Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1};

		end = new int[m][2];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = i;
			end[i][0] = Integer.parseInt(st.nextToken())-1;
			end[i][1] = Integer.parseInt(st.nextToken())-1;
		}

	}
}
