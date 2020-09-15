package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽돌깨기_5656 {
	static int N, W, H, map[][], temp[], min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int testcase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testcase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			min = count_block(map);
			double_permutation(0, map, min);
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void double_permutation(int depth, int map[][], int blockCnt) {
		if (depth == N) {
			// count_block
			min = Math.min(min, blockCnt);
			return;
		}

		for (int i = 0; i < W; i++) {
			//find start block
			int start = find_start_block(i, map);
			if(start == -1) continue;
			
			// copy map
			int copy[][] = copy_array(map);
			
			// break block
			int remove = bfs(start, i, copy);
			// drop block
						drop_block(copy);
			if(blockCnt-remove<=0) {
				min = 0;
				return;
			}
			double_permutation(depth + 1, copy, blockCnt-remove);
		}
	}

	private static int count_block(int[][] map) {
		int num = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] != 0)
					num++;
			}
		}
		return num;
	}

	private static void drop_block(int[][] map) {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < W; i++) {
			for (int j = H - 1; j >= 0; j--) {
				if (map[j][i] != 0) {
					q.offer(map[j][i]);
					map[j][i] = 0;
				}
			}

			int idx = H - 1;
			while (!q.isEmpty()) {
				map[idx--][i] = q.poll();
			}
		}
	}
	private static int find_start_block(int column, int map[][]) {
		for (int i = 0; i < H; i++) {
			if (map[i][column] == 0)
				continue;
			return i;
		}
		return 0;
	}
	static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };

	private static int bfs(int start, int column, int map[][]) {
		int cnt=0;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {start, column, map[start][column]});
		map[start][column] = 0;
		cnt++;
		int y, x;
		while (!q.isEmpty()) {
			temp = q.poll();
			for (int d = 0; d < 4; d++) {
				y = temp[0];
				x = temp[1];
				for (int i = 1; i < temp[2]; i++) {
					y += dy[d];
					x += dx[d];
					if (isRange(y, x) || map[y][x] == 0)
						continue;
					q.offer(new int[] { y, x, map[y][x] });
					map[y][x] = 0;
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static boolean isRange(int y, int x) {
		return y < 0 || x < 0 || y >= H || x >= W;
	}

	private static int[][] copy_array(int[][] map) {
		int arr[][] = new int[map.length][map[0].length];
		for (int i = 0; i < H; i++) {
			arr[i] = map[i].clone();
		}

		return arr;
	}
}
