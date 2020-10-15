package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14499_주사위굴리기 {
	static int N, M, y, x, k, map[][];
	static int[] dy= {0, 0, 0, -1, 1}, dx= {0, 1, -1, 0, 0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken()); x = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		Dice dice = new Dice();
		for(int i=0; i<N; i++ ) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++ ) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<k; i++) {
			//동 서 남 북
			int num = Integer.parseInt(st.nextToken());
			int ny = y + dy[num];
			int nx = x + dx[num];
			if(ny < 0 || nx < 0 || ny>=N || nx>=M) continue;
			if(num==1) dice.turnRight();
			else if(num==2) dice.turnLeft();
			else if(num==3) dice.turnTop();
			else dice.turnBottom();
			y = ny; x = nx;
			if(map[ny][nx]==0) {
				map[ny][nx] = dice.bottom;
			}
			else {
				dice.bottom = map[ny][nx];
				map[ny][nx]	= 0;
			}
			sb.append(dice.top).append("\n");
		}
		System.out.println(sb.toString());
	}
	static class Dice {
		int bottom, top, left, right, front, back;
		private void turnRight() {
			int temp = top;
			top = left; left = bottom; bottom = right; right = temp;
		}
		private void turnLeft() {
			int temp = top;
			top = right; right = bottom; bottom = left; left = temp;
		}
		private void turnTop() {
			int temp = front;
			front = bottom; bottom = back; back = top; top = temp;
		}
		private void turnBottom() {
			int temp = front;
			front = top; top = back; back = bottom; bottom = temp;
		}
	}
}
