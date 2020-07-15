package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2186_문자판 {
	static int n, m, k, dp[][][];
	static char map[][], find[];
	public static void main(String[] args) throws Exception{
		input();
		int rtv = solve();
		System.out.println(rtv);
	}

	private static int solve() {
		return findStart();
	}

	private static int findStart() {
		int sum=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++){
				if(map[i][j]==find[0]) sum+=dfs(i, j, 1);
			}
		}
		return sum;
	}
	static int[] dy= {-1, 0, 1, 0}, dx= {0, 1, 0, -1};
	private static int dfs(int y, int x, int depth) {
		if(depth==find.length) return 1;
		if(dp[y][x][depth]!=-1) return dp[y][x][depth];
		int sum=0, ny, nx;
		for(int d=0; d<4; d++) {
			for(int i=1; i<=k; i++) {
				ny = y + dy[d]*i;
				nx = x + dx[d]*i;
				if(ny<0||nx<0||ny>=n||nx>=m||map[ny][nx]!=find[depth]) continue;
				sum+=dfs(ny, nx, depth+1);
			}
		}
		return dp[y][x][depth]=sum;
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		find = br.readLine().toCharArray();
		dp = new int[n][m][find.length];
		for(int i=0; i<n; i++) for(int j=0; j<m; j++) Arrays.fill(dp[i][j], -1);
	}
}
