package Programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Programmers_디스크컨트롤러 {
	public int solution(int[][] jobs) {
		int time =0, task[], total=0, division=jobs.length;
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		Arrays.sort(jobs, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});
		int idx = jobs.length-1;
		while(idx>=0 || !pq.isEmpty()) {
			while(idx>=0 && time>=jobs[idx][0]) {
				pq.offer(jobs[idx--]);
			}
			
			if(pq.isEmpty()) {
				time = jobs[idx][0];
			}else {
				task = pq.poll();
				total += time - task[0] + task[1];
				time += task[1];
			}
		}
		
		
		return total/division;
	}
}
