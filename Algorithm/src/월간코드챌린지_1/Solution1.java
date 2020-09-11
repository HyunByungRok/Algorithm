package 월간코드챌린지_1;

import java.util.Arrays;

public class Solution1 {
	public static void main(String[] args) {
		int numbers[] = {2,1,3,4,1};
		System.out.println(Arrays.toString(solution(numbers)));
	}
	public static int[] solution(int[] numbers) {
		int[] answer;
        int count = 0;
        boolean used[] = new boolean[201];
        for(int i=0; i<numbers.length; i++) {
        	for(int j=0; j<numbers.length; j++) {
        		if(i==j) continue;
        		int n = numbers[i] + numbers[j];
        		if(used[n]) continue;
        		used[n] = true;
        		count ++;
        	}
        }
        answer = new int[count];
        int idx = 0;
        for(int i=0; i<=200; i++) if(used[i]) answer[idx++] = i;
        return answer;
    }
}


	
