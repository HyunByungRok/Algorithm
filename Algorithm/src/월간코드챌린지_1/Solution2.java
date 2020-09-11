package 월간코드챌린지_1;

import java.util.Arrays;

public class Solution2 {
	public static void main(String[] args) {
		int numbers = 6;
		System.out.println(solution(numbers));
	}
	static int[] dy= {1,0,-1}, dx= {0,1,-1};
	public static int[] solution(int n) {
        int[] answer = new int[n*(n+1)/2];
        int arr[][] = new int[n][n];
        int y = -1, x = 0, value = 1, ny, nx;
        
        found: while(true) {
        	for(int d=0; d<3; d++) {
        		for(int i=0; i<n; i++) {
        			y += dy[d];
        			x += dx[d];
        			arr[y][x] = value++;
        		}
        		if(--n == 0) break found;
        		
        	}
        }
        int idx = 0;
        for(int i=0; i<arr.length; i++) {
        	for(int j=0; j<arr[i].length; j++) {
        		if(arr[i][j]!=0) answer[idx++] = arr[i][j];
        	}
        }
        	
        return answer;
    }
}
