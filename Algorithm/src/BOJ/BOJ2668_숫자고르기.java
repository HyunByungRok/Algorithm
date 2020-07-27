package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2668_숫자고르기 {
	static int n, num[], max=0;
	static boolean visited[], result[];
	public static void main(String[] args) throws Exception{
		input();
		solve();
		print();
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		appendLine(sb, max);
		for(int i=1; i<=n; i++) {
			if(result[i]) appendLine(sb, i);
		}
		System.out.println(sb.toString());
	}
	
	private static void appendLine(StringBuilder sb, int value) {
		sb.append(value).append("\n");
	}
	
	private static void solve() {
		for(int i=1; i<=n; i++) if(!result[i]) {
			dfs(i, num[i], 1);
		}
	}

	private static boolean dfs(int start, int cur, int cnt) {
		if(visited[cur]) return false;
		visited[cur] = true;
		
		if(dfs(start, num[cur], cnt+1)) return result[cur] = true;
		else visited[cur]=false;
		
		if(start == cur) {
			max+=cnt;
			return result[cur] = true;
		}
		//의미없는값
		return false;
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		num = new int[n+1];
		for(int i=1; i<=n; i++) num[i] = Integer.parseInt(br.readLine());
		visited = new boolean[n+1];
		result = new boolean[n+1];
	}
}
