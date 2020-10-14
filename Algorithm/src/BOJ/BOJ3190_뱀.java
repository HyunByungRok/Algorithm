package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3190_뱀 {
	static int N, board[][], K, L, time=1, dir, snake[], endT;
	static Queue<int[]> q = new LinkedList<>();
	static int[] dy= {-1, 0, 1, 0}, dx= {0, 1, 0, -1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		q.offer(new int[] {0, 0});
		snake = new int[] {0, 0};
		board[0][0] = 2;
		dir = 1;
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			endT = Integer.parseInt(st.nextToken());
			move(endT, st.nextToken().charAt(0));
		}
		if(endT<=time)
			move(time+N, 'T');
		System.out.println(time);
	}
	private static void move(int endT, char command) {
		for(; time<=endT; time++) {
			int ny = snake[0]+dy[dir];
			int nx = snake[1]+dx[dir];
			if(isOut(ny, nx) || board[ny][nx]==2) return;
			snake = new int[] {ny, nx};
			q.offer(new int[] {snake[0], snake[1]});
			if(board[snake[0]][snake[1]]==1) {
				board[snake[0]][snake[1]] = 2;
				continue;
			}
			board[snake[0]][snake[1]] = 2;
			int[] temp = q.poll();
			board[temp[0]][temp[1]] = 0;
		}
		dir = changeDir(command);
	}
	private static boolean isOut(int ny, int nx) {
		return ny < 0 || nx < 0 || ny>=N || nx>=N;
	}
	private static int changeDir(char command) {
		return command=='L' ? (dir+3)%4 : (dir+1)%4;
	}
}
