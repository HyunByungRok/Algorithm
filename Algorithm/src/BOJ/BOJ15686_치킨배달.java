package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15686_치킨배달 {
	static int N, M, map[][], dist[][], picked[], min=Integer.MAX_VALUE;
	static List<int[]> homes, chicken;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		picked = new int[M];
		homes = new LinkedList<>();
		chicken = new LinkedList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) homes.add(new int[] {i, j});
				else if(map[i][j]==2) chicken.add(new int[] {i, j});
			}
		}
		dist = new int[homes.size()][chicken.size()];
		getDist();
		combi(0, 0);
		System.out.println(min);
	}
	private static void getDist() {
		for(int i=0; i<homes.size(); i++) {
			int[] home = homes.get(i);
			for(int j=0; j<chicken.size(); j++) {
				int[] ch = chicken.get(j);
				dist[i][j] = Math.abs(home[0]-ch[0]) + Math.abs(home[1]-ch[1]);
			}
		}
	}
	private static void combi(int depth, int idx) {
		if(depth == M) {
			min = Math.min(min, getTdist());
			return;
		}
		for(int i=idx; i<chicken.size(); i++) {
			picked[depth] = i;
			combi(depth+1, i+1);
		}
	}
	private static int getTdist() {
		int total = 0;
		for(int i=0; i<homes.size(); i++) {
			int min = Integer.MAX_VALUE;
			for(int j=0; j<M; j++) {
				min = Math.min(min, dist[i][picked[j]]);
			}
			total+=min;
		}
		return total;
	}
}
