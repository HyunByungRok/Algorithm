package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2933_미네랄 {
	static int r, c, stick[];
	static char map[][];
	//order - false왼오/true오왼
	static boolean order, visited[][];
	static int[] dy= {-1, 0, 1, 0}, dx= {0, 1, 0, -1};
	public static void main(String[] args) throws Exception{
		input();
		solve();
		print();
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++)
				sb.append(map[i][j]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void solve() {
		for(int st=0; st<stick.length; st++) {
			int[] point = throwStick(stick[st]);
			if(point == null) continue;
			checkCluster(point);
		}
	}

	private static void checkCluster(int[] point) {
		//부셔진 점에 대해서 4방향을 탐색하여 결정이 있는 곳이 있는지 확인한다.
		int ny, nx;
		
		for(int d=0; d<4; d++) {
			ny = point[0] + dy[d];
			nx = point[1] + dx[d];
			if(ny<0 || nx<0 || ny>=r || nx>=c ||map[ny][nx] == '.') continue;
			//결정이 있는 곳은 bfs를 통해서 공중에 떨어진지 아닌지 검사한다.
			if(dropCluster(ny, nx)) break;
		}
	}

	private static boolean dropCluster(int y, int x) {
		//bfs 탐색을 하는데 바닥에 있는 애가 그 밑에 존재하는 블럭이랑 가장 가까운 값을 구할 할 것이다.
		Queue<int[]> q = new LinkedList<int[]>();
		List<int[]> list = new ArrayList<int[]>();
		List<int[]> bottom = new ArrayList<int[]>();
		visited = new boolean[r][c];
		int ny, nx, cur[], min = Integer.MAX_VALUE;
		visited[y][x] = true;
		q.offer(new int[] {y, x});
		while(!q.isEmpty()) {
			cur = q.poll();
			list.add(cur);
			if(cur[0]+1<r && map[cur[0]+1][cur[1]]=='.') {
				bottom.add(new int[] {cur[0], cur[1]});
				//공중에 뜬 경우 아래를 검사해서 몇칸 띄어져있는 블럭인지 검사한다.
			}
			if(cur[0] == r-1) return false;
			for(int d=0; d<4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];
				if(ny<0 || nx<0 || ny>=r || nx>=c || visited[ny][nx] ||map[ny][nx] == '.') continue;
				visited[ny][nx] = true;
				q.offer(new int[] {ny, nx});
			}
		}
		for(int[] pos : bottom) {
			int idx = 1;
			while(pos[0]+idx<r && map[pos[0]+idx][pos[1]]=='.') {
				idx++;
			}
			if(pos[0]+idx<r && visited[pos[0]+idx][pos[1]]) continue;
			min = Math.min(min, idx-1);
		}
		
		//가장 작은 차이를 구했으니 떨궈보자... list를 돌며 쫙 지워준 뒤 다시 돌면서 min만큼 더한위치에다 그릴 것이다.
		for(int[] pos : list) {
			map[pos[0]][pos[1]] = '.';
		}
		for(int[] pos : list) {
			map[pos[0]+min][pos[1]] = 'x';
		}
		return true;
	}

	private static int[] throwStick(int height) {
		height = r-height;
		if(order) {
			order=!order;
			for(int i=c-1; i>=0; i--) {
				if(map[height][i] == 'x') {
					map[height][i]='.';
					return new int[] {height, i};
				}
			}
		}else {
			order=!order;
			for(int i=0; i<c; i++) {
				if(map[height][i] == 'x') {
					map[height][i]='.';
					return new int[] {height, i};
				}
			}
		}
		return null;
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for(int i=0; i<r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		stick = new int[Integer.parseInt(br.readLine())];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<stick.length; i++) {
			stick[i] = Integer.parseInt(st.nextToken());
		}
	}
}
