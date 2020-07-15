package Programmers;

import java.util.*;
import java.io.*;

class Programmers_완주하지못한선수 {
	public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String name : completion) {
        	if(map.containsKey(name)) map.replace(name, map.get(name)+1);
        	else map.put(name, 1);
        }
        for(String name : participant) {
        	if(!map.containsKey(name)) {
        		answer = name;
        		break;
        	}else {
        		int count = map.get(name);
        		if(count>1) {
        			map.replace(name, map.get(name)-1);
        		}else if(count==1) {
        			map.remove(name);
        		}
        	}
        }
        return answer;
    }
}
