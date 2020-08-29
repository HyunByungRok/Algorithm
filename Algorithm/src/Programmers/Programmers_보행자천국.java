package Programmers;

import java.util.Arrays;

public class Programmers_보행자천국 {
	static int MOD = 20170805, memo[][][];
	static boolean visited[][];
	static int[] dy= {1, 0}, dx= {0, 1};
    public int solution(int m, int n, int[][] cityMap) {
    	memo = new int[m][n][2];
    	visited = new boolean[m][n];
    	for(int i=0; i<m; i++)
    		for(int j=0; j<n; j++) Arrays.fill(memo[i][j], -1);
    	visited[0][0]=true;
        return dfs(0, 0, -1, m, n, cityMap);
    }
	private int dfs(int y, int x, int dir, int m, int n, int[][] cityMap) {
		if(y==m-1 && x==n-1) return 1;
		else if(dir!=-1 && memo[y][x][dir]!=-1) return memo[y][x][dir];
		int total=0, ny, nx;
		for(int d=0; d<2; d++) {
			if(cityMap[y][x]==2 && d!=dir) continue; 
			ny = y + dy[d];
			nx = x + dx[d];
			if(ny>=m || nx>=n || cityMap[ny][nx]==1) continue;
			total+=dfs(ny, nx, d, m, n, cityMap);
		}
		
		if(cityMap[y][x] == 2) return memo[y][x][dir] = total%MOD;
		return memo[y][x][0] = memo[y][x][1] = total%20170805;
	}
}
