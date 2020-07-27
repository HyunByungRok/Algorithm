package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1507_궁금한민호 {
	static int n, dist[][], d, total;
	public static void main(String[] args) throws Exception{
		input();
		solve();
		System.out.println(total);
	}

	private static void solve() {
		total/=2;
		floydW();
	}

	private static void floydW() {
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(dist[i][k]==0 || dist[k][j]==0) continue;
					d = dist[i][k] + dist[k][j];
					if(dist[i][j]==d) {
						total-=dist[i][j];
						dist[i][j]=dist[j][i]=0;
					}else if(dist[i][j]>d) {
						total=-1;
						return;
					}
				}
			}
		}
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dist = new int[n][n];
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				total += (dist[i][j] = Integer.parseInt(st.nextToken()));
			}
		}
	}
}
