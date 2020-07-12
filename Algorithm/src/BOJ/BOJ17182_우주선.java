package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17182_우주선 {
	static int n, k, t[][], time[][], dp[][];
	public static void main(String[] args) throws Exception{
		input();
		int rtv = solve();
		System.out.println(rtv);
	}

	private static int solve() {
		floyd();
		return tsp(k, 1<<k);
	}

	private static int tsp(int now, int visited) {
		if(dp[now][visited]!=-1) {
			return dp[now][visited];
		}
		if(visited==((1<<n)-1)) {
			return dp[now][visited]=0;
		}
		int num = Integer.MAX_VALUE;
		for(int i=0; i<n; i++) {
			if((visited&(1<<i)) > 0) continue;
			num = Math.min(num, tsp(i, visited|(1<<i)) + t[now][i]);
		}
		return dp[now][visited]=num;
	}

	private static void floyd() {
		for(int l=0; l<n; l++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(t[i][l]+t[l][j] >= t[i][j]) continue;
					t[i][j] = t[i][l]+t[l][j];
				}
			}
		}
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		t = new int[n][n];
		dp = new int[n][1<<n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], -1);
			for(int j=0; j<n; j++) {
				t[i][j] = Integer.parseInt(st.nextToken());
				if(i==j) t[i][j]=987654321;
			}
		}
	}
}
