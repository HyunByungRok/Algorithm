package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11403_경로찾기 {
	static int n, value[][];
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		input();
		solve();
		print();
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				sb.append(value[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void solve() {
		for(int i=0; i<n; i++) {
			Arrays.fill(visited, false);
			dfs(i, i);
		}
	}


	private static void dfs(int start, int now) {
		for(int i=0; i<n; i++) {
			if(visited[i] || value[now][i]==0) continue;
			visited[i]=true;
			value[start][i] = 1;
			dfs(start, i);
		}
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		value = new int[n][n];
		visited = new boolean[n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				value[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
