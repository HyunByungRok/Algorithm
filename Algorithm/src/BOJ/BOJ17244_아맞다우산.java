package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17244_아맞다우산 {
	static int n, m, s[], e[], distance[][], dp[][];
	static char map[][];
	static List<int[]> object;
	public static void main(String[] args) throws IOException{
		input();
		
		System.out.println(solve());
	}

	private static int solve() {
		findEachDistance();
		dp = new int[object.size()][1<<object.size()];
		for(int i=0; i<object.size(); i++) Arrays.fill(dp[i], -1);
		return go(0, 1<<0);
	}

	private static int go(int now, int visited) {
		if(dp[now][visited]!=-1) return dp[now][visited];
		else if(visited == (1<<(object.size()-1))-1) {
			return dp[now][visited]=distance[now][object.size()-1];
		}
		int num = Integer.MAX_VALUE;
		for(int i=1; i<object.size()-1; i++) {
			if((visited&(1<<i)) > 0) continue;
			num = Math.min(num, go(i, visited|(1<<i)) + distance[now][i]);
		}
		return dp[now][visited]=num;
	}

	private static void findEachDistance() {
		distance = new int[object.size()][object.size()];
		for(int i=0; i<object.size(); i++) bfs(i);
	}
	static int[] dy= {-1, 0, 1, 0}, dx= {0, 1, 0, -1};
	private static void bfs(int i) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(object.get(i));
		boolean visited[][] = new boolean[m][n];
		visited[q.peek()[0]][q.peek()[1]]=true;
		
		int qSize, count=0, temp[], ny, nx;
		while(!q.isEmpty()) {
			qSize = q.size();
			count++;
			for(int qs=0; qs<qSize; qs++) {
				temp = q.poll();
				for(int d=0; d<4; d++) {
					ny = temp[0]+dy[d];
					nx = temp[1]+dx[d];
					if(ny<0||nx<0||ny>=m||nx>=n||visited[ny][nx]||map[ny][nx]=='#') continue;
					if(map[ny][nx]>=0 && map[ny][nx]<object.size()) {
						distance[i][map[ny][nx]]=count;
					}
					visited[ny][nx]=true;
					q.offer(new int[] {ny, nx});
				}
			}
		}
	}

	private static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		object = new ArrayList<int[]>();
		map = new char[m][n];
		int number=1;
		for(int i=0; i<m; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<n; j++) {
				if(map[i][j]=='S') s = new int[] {i,j};
				else if(map[i][j]=='E') e = new int[] {i,j};
				else if(map[i][j]=='X') {
					map[i][j] = (char)number++;
					object.add(new int[] {i,j});
				}
			}
		}
		object.add(0, s);
		object.add(e);
		map[e[0]][e[1]]=(char) number;
	}
}
