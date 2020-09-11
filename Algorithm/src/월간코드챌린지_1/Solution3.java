package 월간코드챌린지_1;

import java.util.HashSet;
import java.util.Set;

public class Solution3 {
	public static void main(String[] args) {
		int numbers[] = {-16,27,65,-2,58,-92,-71,-68,-61,-33};
		System.out.println(solution(numbers));
	}
    public static int solution(int[] a) {
        Set<Integer> set = new HashSet<>();
        int left = Integer.MAX_VALUE, right=Integer.MAX_VALUE, num;
        for(int i=1; i<a.length; i++) right = Math.min(right, a[i]);
        for(int i=1; i<a.length; i++) {
        	if(left>a[i-1]) {
        		left = a[i-1];
        		
        	}
        	if(right==a[i]) {
        		if(i+1<a.length) {
        			right = a[i+1];
        		}
        	}
        	set.add(left);
        	set.add(right);
        	
        }
        
        return set.size();
    }

}
