package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2225_합분해 {
	static int n, k, dp[][], rtv;
	public static void main(String[] args) throws Exception{
		input();
		rtv = solve();
		System.out.println(rtv);
	}

	private static int solve() {
		return topDown(n, 0);
	}

	private static int topDown(int num, int depth) {
		if(depth==k) {
			if(num==0) return 1;
			else return 0;
		}
		if(dp[num][depth]!=-1) {
			return dp[num][depth]%1000000000;
		}
		int sum=0;
		for(int i=0; i<=n; i++) {
			if(num-i<0) break;
			sum=(sum+topDown(num-i, depth+1))%1000000000;
		}
		return dp[num][depth]=sum%1000000000;
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dp = new int[n+1][k];
		for(int i=0; i<=n; i++) Arrays.fill(dp[i], -1);
		
	}
}
