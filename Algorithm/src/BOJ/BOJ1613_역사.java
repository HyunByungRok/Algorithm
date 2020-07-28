package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1613_역사 {
	static int n, k, s,e;
	static boolean dist[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); k = Integer.parseInt(st.nextToken());
		dist = new boolean[n+1][n+1];
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken()); e = Integer.parseInt(st.nextToken());
			dist[s][e] = true;
		}
		
		floydW();
		StringBuilder sb = new StringBuilder();
		k = Integer.parseInt(br.readLine());
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			if(dist[s= Integer.parseInt(st.nextToken())][e= Integer.parseInt(st.nextToken())]) sb.append(-1).append("\n");
			else if(dist[e][s]) sb.append(1).append("\n");
			else sb.append(0).append("\n");
		}
		System.out.println(sb.toString());
		
	}
	private static void floydW() {
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				if(!dist[i][k]) continue;
				for(int j=1; j<=n; j++) {
					if(dist[k][j]) dist[i][j]=true;
				}
			}
		}
	}
}
