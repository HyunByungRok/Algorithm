package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1039_교환 {
	static int n, k, max, len;
	static boolean visited[][];
	static final int MAX_SIZE = 1000001;
	public static void main(String[] args) throws Exception{
		input();
		solve();
		System.out.println(max==0 ? -1 : max);
	}

	private static void solve() {
		recursion(n, 0);
	}

	private static void recursion(int num, int depth) {
		if(depth==k) {
			max = Math.max(max, num);
			return;
		}
		char[] cArr = String.valueOf(num).toCharArray();
		for(int i=0; i<len; i++) {
			for(int j=i+1; j<len; j++) {
				if(i==0 && cArr[j]=='0') continue;
				int after = swap(cArr, i, j);
				if(!visited[after][depth]) {
					visited[after][depth]=true;
					recursion(after, depth+1);
				}
				swap(cArr, j, i);
			}
		}
	}

	private static int swap(char[] cArr, int i, int j) {
		char temp = cArr[i];
		cArr[i] = cArr[j];
		cArr[j] = temp;
		return Integer.parseInt(String.valueOf(cArr));
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		visited = new boolean[MAX_SIZE][k];
		max = 0;
		len = String.valueOf(n).length();
	}
}
