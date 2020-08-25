package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1941_소무난칠공주 {
	static final int SIZE = 5;
	static final char 이다솜파 = 'S';
	static char map[][];
	static boolean visited[];
	public static void main(String[] args) throws Exception{
		input();
		System.out.println(solve());
	}
	private static int solve() {
		int rtv = 0;
		for(int i=0; i<SIZE*SIZE; i++) {
			int y = i/SIZE;
			int x = i%SIZE;
			if(map[y][x]==이다솜파) {
				rtv+=dfs(1, 0, 1<<i);
			}else {
				rtv+=dfs(0, 1, 1<<i);
			}
		}
		return rtv;
	}
	static int[] dy= {-1, 0, 1, 0}, dx= {0, 1, 0, -1};
	private static int dfs(int dasom, int doyun, int bitV) {
		if(doyun > 3) return 0;
		if(dasom + doyun == 7) {
			return 1;
		}

		int rtv = 0;
		for(int i=0; i<SIZE*SIZE; i++) {
			if((bitV&(1<<i)) == 0) continue;
			int y = i/SIZE;
			int x = i%SIZE;
			for(int d=0; d<4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				int nextV = SIZE*ny + nx;
				if(ny < 0 || nx < 0 || ny>=SIZE || nx>=SIZE || visited[bitV|(1<<nextV)]) continue;
				visited[bitV|(1<<nextV)] = true;
				if(map[ny][nx] == 이다솜파) {
					rtv += dfs(dasom+1, doyun, bitV|(1<<nextV));
				}else {
					rtv += dfs(dasom, doyun+1, bitV|(1<<nextV));
				}
			}
		}
		return rtv;
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[SIZE][SIZE];
		for(int i=0; i<SIZE; i++) {
			map[i] = br.readLine().toCharArray();
		}
		visited = new boolean[1<<(SIZE*SIZE)];
	}
}
