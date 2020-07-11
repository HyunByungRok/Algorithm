package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9328_열쇠 {
	static int key, h, w, temp[], rtv;
	static char map[][];
	static boolean visited[][];
	static int[] dy= {0, 1, 0, -1}, dx= {1, 0, -1, 0};
	static Queue<int[]> q, doors[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testcase = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=testcase; tc++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			rtv=0;
			for(int i=0; i<h; i++) {
				map[i] = br.readLine().toCharArray();
			}
			String keyStr = br.readLine();
			key=0;
			if(!keyStr.equals("0")) {
				for(int i=0; i<keyStr.length(); i++) {
					key |= (1<<(keyStr.charAt(i)-'a'));
				}
			}
			q = new LinkedList<int[]>();
			doors = new Queue[26];
			for(int i=0; i<26; i++) doors[i] = new LinkedList<int[]>();
			visited = new boolean[h][w];
			int y=0, x=0;
			for(int d=0; d<4; d++) {
				while(true) {
					if(!visited[y][x]&&possible(y, x)) {
						q.add(new int[] {y, x});
						visited[y][x] = true;
					}
					y+=dy[d];
					x+=dx[d];
					if(y<0||x<0||y>=h||x>=w) {
						y-=dy[d];
						x-=dx[d];
						break;	
					}
				}
			}
			bfs();
			System.out.println(rtv);
		}
	}

	private static void bfs() {
		int ny,nx;
		while(!q.isEmpty()) {
			temp = q.poll(); 
			for(int d=0; d<4; d++) {
				ny = temp[0]+dy[d];
				nx = temp[1]+dx[d];
				
				if(ny<0||nx<0||ny>=h||nx>=w||visited[ny][nx]|| map[ny][nx]=='*') continue;
				if(map[ny][nx]>='a' && map[ny][nx]<='z') {
					key |= (1<<(map[ny][nx]-'a'));
					while(!doors[map[ny][nx]-'a'].isEmpty()) {
						q.add(doors[map[ny][nx]-'a'].poll());
					}
				}else if(map[ny][nx]>='A' && map[ny][nx]<='Z') {
					if((key&(1<<(map[ny][nx]-'A')))==0) {
						doors[map[ny][nx]-'A'].add(new int[] {ny, nx});
						continue;
					}
				}else if(map[ny][nx]=='$') {
					rtv++;
				}
				visited[ny][nx]=true;
				q.offer(new int[] {ny, nx});
			}
					
		}
	}
	private static boolean possible  (int y, int x) {
		if(map[y][x]=='.') {
			return true;
		}else if(map[y][x]>='A' && map[y][x]<='Z') {
			if((key&(1<<(int)(map[y][x]-'A'))) == 0){
				doors[map[y][x]-'A'].add(new int[] {y, x});
				visited[y][x]=true;
				return false;
			}
			return true;
		}else if(map[y][x]>='a' && map[y][x]<='z') {
			key |= (1<<(map[y][x]-'a'));
			while(!doors[map[y][x]-'a'].isEmpty()) {
				q.add(doors[map[y][x]-'a'].poll());
			}
			return true;
		}else if(map[y][x]=='$') {
			rtv++;
			return true;
		}
		return false;
	}
}
