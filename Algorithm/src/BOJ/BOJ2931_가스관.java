package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2931_가스관 {
	static int r, c, m[], z[], ny, nx, temp[], nd, rtv[];
	static char map[][];
	static boolean visited[][];
	public static void main(String[] args) throws Exception{
		input();
		solve();
		System.out.println((rtv[0]+1) + " " + (rtv[1]+1) + " " + blockKind(rtv[2]));
	}

	private static char blockKind(int num) {
		if(num==5) {
			return '|';
		}else if(num==10) {
			return '-';
		}else if(num==15) {
			return '+';
		}else if(num==6) {
			return '1';
		}else if(num==3) {
			return '2';
		}else if(num==9) {
			return '3';
		}else if(num==12) {
			return '4';
		}
		return 'f';
	}

	static int[] dy= {-1, 0, 1, 0}, dx= {0, 1, 0, -1};
	private static void solve() {
		Queue<int[]> q = new LinkedList<int[]>();
		visited[m[0]][m[1]]=true;
		for(int d=0; d<4; d++) {
			ny = m[0]+dy[d];
			nx = m[1]+dx[d];
			if(ny<0||nx<0||ny>=r||nx>=c||map[ny][nx]=='.') continue;
			q.offer(new int[] {ny, nx});
			break;
		}
		
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int d=0; d<4; d++) {
				if((map[temp[0]][temp[1]]&(1<<d)) == 0) continue;
				ny = temp[0]+dy[d];
				nx = temp[1]+dx[d];
				if(ny<0||nx<0||ny>=r||nx>=c||visited[ny][nx]) continue;
				visited[ny][nx]=true;
				if(map[ny][nx]=='.') {
					if(isEnd(ny, nx, d)) {
						rtv[0]=ny;
						rtv[1]=nx;
						return;
					}
					continue;
				}else if(map[ny][nx]=='Z') {
				}else if(((1<<((d+2)%4))&map[ny][nx]) > 0){
					q.offer(new int[] {ny, nx});
					
				}
			}
		}		
	}

	private static boolean isEnd(int y, int x, int dir) {
		int tempY, tempX;
		rtv[2]=(1<<((dir+2)%4));
		for(int d=0; d<4; d++) {
			if(((dir+2)%4) == d ) continue;
			tempY = y + dy[d];
			tempX = x + dx[d];
			if(tempY<0 || tempX<0 || tempY>=r || tempX>=c || map[tempY][tempX]=='.' || map[tempY][tempX]=='Z') continue;
			if((map[tempY][tempX]&(1<<((d+2)%4))) == 0) continue;
			rtv[2] |= (1<<d);
		}
		if(rtv[2]!=(1<<((dir+2)%4))) return true;
		return false;
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		visited = new boolean[r][c];
		rtv = new int[3];
		for(int i=0; i<r; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<c; j++) {
				if(map[i][j]=='|') {
					map[i][j]=5;
				}else if(map[i][j]=='-') {
					map[i][j]=10;
				}else if(map[i][j]=='+') {
					map[i][j]=15;
				}else if(map[i][j]=='1') {
					map[i][j]=6;
				}else if(map[i][j]=='2') {
					map[i][j]=3;
				}else if(map[i][j]=='3') {
					map[i][j]=9;
				}else if(map[i][j]=='4') {
					map[i][j]=12;
				}else if(map[i][j]=='M') {
					m = new int[] {i, j};
				}else if(map[i][j]=='Z') {
					z = new int[] {i, j};
				}
			}
		}
	}
}
