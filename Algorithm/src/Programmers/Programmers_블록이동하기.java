package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_블록이동하기 {
	public int solution(int[][] board) {
        int answer = bfs(board);
        return answer;
    }
	int[] dy= {1, 0, -1, 0}, dx= {0, 1, 0, -1};
	private int bfs(int[][] board) {
		Queue<int[]> q = new LinkedList<int[]>();
		int n = board.length, count = 0, temp[], qSize;
		boolean visited[][][] = new boolean[n][n][2];
		visited[0][0][1] = true; // 1- 가로 | 0 - 세로
		//가로면 무조건 왼쪽꺼 기준 세로면 무조건 위쪽 기준으로 체크
		q.offer(new int[] {0, 0, 1});
		
		int oy, ox, ay, ax, od, ad;
		int noy, nox, nay, nax;
		while(!q.isEmpty()) {
			qSize = q.size();
			for(int qs=0; qs<qSize; qs++) {
				temp = q.poll();
				oy = temp[0]; ox = temp[1]; od = temp[2];
				ay = temp[0]+dy[od];
				ax = temp[1]+dx[od];
				if(isFinish(oy, ox, n) || isFinish(ay, ax, n)) return count;
				//일반적인 4방향 이동
				for(int d=0; d<4; d++) {
					noy = oy + dy[d];
					nox = ox + dx[d];
					nay = ay + dy[d];
					nax = ax + dx[d];
					if(isOut(noy, nox, n) || isOut(nay, nax, n) || board[noy][nox]==1 || board[nay][nax]==1) continue;
					if(visited[noy][nox][od]) continue;
					q.offer(new int[] {noy, nox, od});
					visited[noy][nox][od] = true;
				}
				//회전

				if(od == 0) {
					//세로로 세워진 애들은 좌우로 돌릴 수 있나 없나 검사..	
					if(ox + 1 < n) { 
						if(board[oy][ox+1]!=1 && board[ay][ax+1]!=1) {
							if(!visited[oy][ox][1]) {
								q.offer(new int[] {oy, ox, 1});
								visited[oy][ox][1] = true;
							}
							if(!visited[ay][ax][1]) {
								q.offer(new int[] {ay, ax, 1});
								visited[ay][ax][1] = true;
							}
						}
					}
					if(ox - 1 >= 0) {
						if(board[oy][ox - 1]!=1 && board[ay][ax - 1]!=1) {
							if(!visited[oy][ox-1][1]) {
								q.offer(new int[] {oy, ox-1, 1});
								visited[oy][ox-1][1] = true;
							}
							if(!visited[ay][ax-1][1]) {
								q.offer(new int[] {ay, ax-1, 1});
								visited[ay][ax-1][1] = true;
							}
						}
					}
				}else {
					//가로인 애들은 위 아래로..
					if(oy + 1 < n) { 
						if(board[oy + 1][ox]!=1 && board[ay+1][ax]!=1) {
							if(!visited[oy][ox][0]) {
								q.offer(new int[] {oy, ox, 0});
								visited[oy][ox][0] = true;
							}
							if(!visited[ay][ax][0]) {
								q.offer(new int[] {ay, ax, 0});
								visited[ay][ax][0] = true;
							}
						}
					}
					if(oy - 1 >= 0) {
						if(board[oy - 1][ox]!=1 && board[ay-1][ax]!=1) {
							if(!visited[oy-1][ox][0]) {
								q.offer(new int[] {oy-1, ox, 0});
								visited[oy-1][ox][0] = true;
							}
							if(!visited[ay-1][ax][0]) {
								q.offer(new int[] {ay-1, ax, 0});
								visited[ay-1][ax][0] = true;
							}
						}
					}
				}
			}
			count++;
		}
		
		return -1;
	}
	private boolean isOut(int y, int x, int n) {
		return y<0 || x<0 || y>=n || x>=n;
	}
	private boolean isFinish(int y, int x, int n) {
		return y==n-1 && x==n-1;
	}
}
