package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13458_시험감독 {
	static int N, A, B, C;
    static long total;
	static int perNum[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		perNum = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			perNum[i] = Integer.parseInt(st.nextToken());
		}
		st= new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
		total = N;
		for(int i=0; i<N; i++ ) {
			perNum[i]-=B;
			if(perNum[i] > 0) total += (perNum[i]%C==0 ? perNum[i]/C : perNum[i]/C+1);
		}
		System.out.println(total);
		
	}
}
