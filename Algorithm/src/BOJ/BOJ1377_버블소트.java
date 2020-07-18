package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ1377_버블소트 {
	static int n, a[][];
	public static void main(String[] args) throws Exception{
		input();
		int rtv =solve();
		System.out.println(rtv);
	}

	private static int solve() {
		sorting();
		return compare_idx();
	}


	private static int compare_idx() {
		int maxDiff=0;
		for(int i=0; i<n; i++) {
			if(a[i][1]-i > maxDiff) maxDiff = a[i][1]-i;
		}
		return maxDiff+1;
	}

	private static void sorting() {
		Arrays.parallelSort(a, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		a = new int[n][2];
		for(int i=0; i<n; i++) {
			a[i][0] = Integer.parseInt(br.readLine());
			a[i][1] = i;
		}
	}
}
