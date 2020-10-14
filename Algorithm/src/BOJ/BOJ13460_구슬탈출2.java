package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13460_구슬탈출2 {
	static int N, M, blue[], red[];
	static char map[][];
	static boolean visited[][][][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'R')
					red = new int[] { i, j };
				else if (map[i][j] == 'B')
					blue = new int[] { i, j };
			}
		}

		System.out.println(bfs());
	}

	static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };

	private static int bfs() {
      Queue<int[]> blueQ, redQ;
      blueQ = new LinkedList<>();
      redQ = new LinkedList<>();
      blueQ.offer(blue); redQ.offer(red);
      visited[blue[0]][blue[1]][red[0]][red[1]] = true;
      
      int qSize, b[], r[], count=0;
      while(!blueQ.isEmpty()) {
         qSize = blueQ.size();
         if(++count>10) break;
         for(int qs=0; qs<qSize; qs++ ) {
            b = blueQ.poll();
            r = redQ.poll();
            for(int d=0; d<4; d++) {
               int rTime=0, bTime=0;
               int nb[] = b.clone();
               int nr[] = r.clone();
               while(!isUnvalid(nb, d)) {
            	   nb[0]+=dy[d];
            	   nb[1]+=dx[d];
            	   bTime++;
               }
               
               while(!isUnvalid(nr, d)) {
            	   nr[0]+=dy[d];
            	   nr[1]+=dx[d];
            	   rTime++;
               }
               
               if(map[nr[0]][nr[1]]=='O') {
            	   if(map[nb[0]][nb[1]]=='O') continue;
            	   return count;
               }
               if(map[nb[0]][nb[1]]=='O') continue;
               if(nb[0]==nr[0] && nb[1]==nr[1]) {
            	   if(rTime < bTime) {
            		   nb[0]-=dy[d];
            		   nb[1]-=dx[d];
            	   }else {
            		   nr[0]-=dy[d];
            		   nr[1]-=dx[d];
            	   }
               }
               
               if(visited[nb[0]][nb[1]][nr[0]][nr[1]]) continue;
               visited[nb[0]][nb[1]][nr[0]][nr[1]] = true;
               redQ.offer(nr);
               blueQ.offer(nb);
            }
         }
      }
      return -1;
   }

	private static boolean isUnvalid(int[] pos, int d) {
		int y = pos[0] + dy[d];
		int x = pos[1] + dx[d];
		return y < 0 || y >= N || x < 0 || x >= M || map[y][x] == '#' || map[pos[0]][pos[1]] == 'O';
	}
}