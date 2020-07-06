package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ1525_퍼즐 {
	static final int SIZE = 3;
	static String map="";
	static Set<String> visited = new HashSet<String>();
	public static void main(String[] args) throws Exception{
		input();
		if(isSuccess(map)) System.out.println(0);
		else System.out.println(solve());
	}

	private static int solve() {
		return bfs();
	}

	static int[] dy= {-1, 0, 1, 0}, dx= {0, 1, 0, -1};
	private static int bfs() {
		Queue<String> q = new LinkedList<String>();
		q.offer(map);
		check(map);
		visited.add(map);
		
		int qSize, count=0, ny, nx ,temp[];
		String str, afterStr;
		while(!q.isEmpty()) {
			qSize = q.size();
			count++;
			for(int qs=0; qs<qSize; qs++) {
				str = q.poll();
				temp = strToPos(str);
				for(int d=0; d<4; d++) {
					ny = temp[0] + dy[d];
					nx = temp[1] + dx[d];
					if(ny<0||nx<0||ny>=SIZE||nx>=SIZE) continue;
					afterStr = afterStr(str, ny, nx);
					if(check(afterStr)) continue;
					if(isSuccess(afterStr)) return count;
					q.offer(afterStr);
					visited.add(afterStr);
				}
			}
		}
		return -1;
	}


	private static boolean isSuccess(String str) {
		return str.equals("123456780");
	}

	private static String afterStr(String str, int ny, int nx) {
		int idx1 = str.indexOf("0");
		char cArr[] = str.toCharArray();
		int idx2 = ny*SIZE + nx;
		char temp = cArr[idx1];
		cArr[idx1] =  cArr[idx2];
		cArr[idx2] = temp;
		return String.valueOf(cArr);
	}

	private static int[] strToPos(String str) {
		int num = str.indexOf("0");
		return new int[] {num/SIZE, num%SIZE};
	}

	private static void input() throws Exception{ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i=0; i<SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<SIZE; j++) map+=st.nextToken();
		}
		
	}
	
	private static boolean check(String str) {
		return visited.contains(str);
	}

}
