package Programmers;

import java.util.LinkedList;
import java.util.Queue;

class Programmers_다리를지나는트럭 {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer=0, curWeight=0, time=0, idx=0;
		Queue<Integer> timeQ = new LinkedList<Integer>();
		Queue<Integer> truckQ = new LinkedList<Integer>();
		while(true) {
			time++;
			if(!timeQ.isEmpty() && timeQ.peek()<=time) {
				timeQ.poll();
				curWeight-=truckQ.poll();
			}
			if(idx<truck_weights.length && weight>=truck_weights[idx]+curWeight) {
				truckQ.offer(truck_weights[idx]);
				curWeight+=truck_weights[idx++];
				timeQ.offer(bridge_length+time);
			}
			if(idx==truck_weights.length) break;
		}
		for(Integer i : timeQ) answer = i;
		return answer;
	}
}
