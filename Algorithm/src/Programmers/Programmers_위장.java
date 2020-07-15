package Programmers;

import java.util.HashMap;
import java.util.Map;

class Programmers_위장 {
	public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String[] item : clothes) {
        	if(map.containsKey(item[1])) map.replace(item[1], map.get(item[1])+1);
        	else map.put(item[1], 1);
        }
        
        for(Integer count : map.values()) answer*=(count+1);
        return answer-1;
    }
}
