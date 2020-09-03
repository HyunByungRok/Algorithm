package Programmers;

public class Programmers_외벽점검 {
	int min = Integer.MAX_VALUE;
	boolean visited[];
	public int solution(int n, int[] weak, int[] dist) {
		visited = new boolean[dist.length];
		for(int i=0; i<weak.length; i++) {
			permu(0, i, 0, n, weak, dist);
		}

		return min == Integer.MAX_VALUE ? -1 : min;
	}
	private void permu(int depth, int idx, int count, int n, int[] weak, int[] dist) {
		if(depth >= min) return;
		if(depth == dist.length || count >= weak.length) {
			if(count < weak.length) return;
			min = Math.min(min, depth);
			return;
		}

		for(int i=0; i<dist.length; i++) {
			if(visited[i]) continue;
			int tempIdx = idx, tempCount = count;
			int next = weak[tempIdx] + dist[i];
			while(weak[tempIdx]  <= next) {
				tempIdx++;
				tempCount++;
				if(tempIdx == weak.length) {
					tempIdx=0;
					if(next>=n)
						next%=n;
					else
						break;
				}
			}
			
			visited[i] = true;
			permu(depth+1, tempIdx, tempCount, n, weak, dist);
			visited[i] = false;
		}
	}
}
