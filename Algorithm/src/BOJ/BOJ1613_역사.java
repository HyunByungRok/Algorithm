package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1613_역사 {
	static int n, k, dist[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); k = Integer.parseInt(st.nextToken());
		dist = new int[n+1][n+1];
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
			dist[s][e] = 1;
		}
		
		floydW();
		StringBuilder sb = new StringBuilder();
		k = Integer.parseInt(br.readLine());
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
			if(dist[s][e]>0) appendLine(sb, 1);
			else if(dist[e][s]>0) appendLine(sb, -1);
			else if(dist[s][e]==0) appendLine(sb, 0);
		}
		System.out.println(sb.toString());
		
	}
	private static void appendLine(StringBuilder sb, int i) {
		sb.append(i).append("\n");
	}
	private static void floydW() {
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(dist[i][k]==0 || dist[k][j]==0) continue;
					int d = dist[i][k] + dist[k][j];
					if(d==0) continue;
					if(d>dist[i][j]) dist[i][j]=d;
				}
			}
		}
	}
}
