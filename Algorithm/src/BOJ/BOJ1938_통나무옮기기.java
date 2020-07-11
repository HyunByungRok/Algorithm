package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1938_통나무옮기기 {
	static int n, s[], less[], more[] ;// s - { y, x, 방향} 0이면 세로 1이면 가로
	static char map[][];
	static boolean visited[][][];
	public static void main(String[] args) throws Exception{
		input();
		int rtv = solve();
		System.out.println(rtv);
	}
	static int[] dy= {-1, 1, 0, 0}, dx= {0, 0, 1, -1};
	private static int solve() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(s);
		visited[s[0]][s[1]][s[2]]=true;
		
		int ny, nx, count=0, qSize;
		while(!q.isEmpty()) {
			qSize = q.size();
			for(int qs=0; qs<qSize; qs++) {
				s = q.poll();
				if(map[s[0]][s[1]]=='E') {
					if(s[2]==0) {
						if(map[s[0]-1][s[1]]=='E' && map[s[0]+1][s[1]]=='E') return count;
					}else {
						if(map[s[0]][s[1]-1]=='E' && map[s[0]][s[1]+1]=='E') return count;
					}
				}
				for(int d=0; d<4; d++) {
					ny = s[0] + dy[d];
					nx = s[1] + dx[d];
					if(ny<0||nx<0||ny>=n||nx>=n||visited[ny][nx][s[2]]||map[ny][nx]=='1') continue;
					less = s[2]==0 ? new int[] {ny-1, nx} : new int[] {ny, nx-1};
					more = s[2]==0 ? new int[] {ny+1, nx} : new int[] {ny, nx+1};
					if(s[2]==0) {
						if(less[0]<0 || more[0]>=n || map[less[0]][nx] == '1' || map[more[0]][nx]=='1') continue;
					}else {
						if(less[1]<0 || more[1]>=n || map[ny][less[1]]=='1' || map[ny][more[1]]=='1') continue;
					}
					q.offer(new int[] {ny, nx, s[2]});
					visited[ny][nx][s[2]] = true;
				}
				
				//90도 회전
				boolean possible=true;
				exit : for(int i=-1; i<=1; i++) {
					for(int j=-1; j<=1; j++) {
						ny = s[0]+i;
						nx = s[1]+j;
						if(ny<0||nx<0||ny>=n||nx>=n||map[ny][nx]=='1') {
							possible=false;
							break exit;
						}
					}
				}
				int nd = (s[2]+1)%2;
				if(possible && !visited[s[0]][s[1]][nd]) {
					q.offer(new int[] {s[0], s[1], nd});
					visited[s[0]][s[1]][nd] = true;
				}
			}
			count++;
		}
		return 0;
	}
	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		visited = new boolean[n][n][2];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]=='B') {
					if(0<=i-1 && i+1<n && map[i-1][j]=='B' && map[i+1][j]=='B') {
						s = new int[] {i, j, 0};
						map[i][j]='.'; map[i-1][j]='.'; map[i+1][j]='.';
					}else if(0<=j-1 && j+1<n && map[i][j-1]=='B' && map[i][j+1]=='B') {
						s = new int[] {i, j, 1};
						map[i][j]='.'; map[i][j-1]='.'; map[i][j+1]='.';
					}
				}
			}
		}
	}
}
