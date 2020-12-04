package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ1417_국회의원선거 {
	static int N, me, answer=0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		me = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		for(int i=0; i<N-1; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		while(!pq.isEmpty()) {
			Integer temp = pq.poll();
			if(temp < me) break;
			me++;
			pq.offer(temp-1);
			answer++;
		}
		System.out.println(answer);
	}
}
