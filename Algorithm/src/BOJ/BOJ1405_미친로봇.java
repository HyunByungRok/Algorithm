package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1405_미친로봇 {
	static int n;
	static double rate[] = new double[4], total;
	static boolean visited[][];
	public static void main(String[] args) throws Exception{
		input();
		solve();
		System.out.println(total);
	}

	private static void solve() {
		visited[n][n]=true;
		overPermu(n, n, 0, 1);
	}
	static int[] dy= {0, 0, 1, -1}, dx= {1, -1, 0, 0};
	private static void overPermu(int y, int x, int depth, double curRate) {
		if(depth==n) {
			total+=curRate;
			return;
		}
		int ny, nx;
		for(int i=0; i<4; i++) {
			ny=y+dy[i];
			nx=x+dx[i];
			if(visited[ny][nx]) continue;
			visited[ny][nx] = true;
			overPermu(ny, nx, depth+1, curRate*rate[i]);
			visited[ny][nx] = false;
		}
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		visited = new boolean[n*2+1][n*2+1];
		for(int i=0; i<4; i++) rate[i] = Double.parseDouble(st.nextToken())/100;
	}
}
