package Programmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Programmers_베스트앨범 {
	class Song{
		int number;
		int plays;
		String genre;
		public Song(int number, int plays, String genre) {
			super();
			this.number = number;
			this.plays = plays;
			this.genre = genre;
		}
		
	}
	public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        List<Song> sList = new ArrayList<>();
        Map<String, Integer> totalPlays = new HashMap<String, Integer>();

        for(int i=0; i<genres.length; i++) {
        	sList.add(new Song(i, plays[i], genres[i]));
        	if(!totalPlays.containsKey(genres[i])) {
        		totalPlays.put(genres[i], plays[i]);
        	}else {
        		totalPlays.replace(genres[i], totalPlays.get(genres[i])+plays[i]);
        	}
        }

        
        sList.sort(new Comparator<Song>() {
			@Override
			public int compare(Song o1, Song o2) {
				if(o1.genre.equals(o2.genre)) {
					if(o1.plays==o2.plays) return o1.number-o2.number;
					return o2.plays - o1.plays;
				}
				return totalPlays.get(o2.genre) - totalPlays.get(o1.genre);
			}
		});
        
        List<Integer> tempAnswer = new ArrayList<Integer>();
        int count=1;
        String lastName=sList.get(0).genre;
        tempAnswer.add(sList.get(0).number);
        for(int i=1; i<sList.size(); i++) {
        	Song temp = sList.get(i);
        	if(lastName.equals(temp.genre)) {
        		if(count==2) continue;
        		tempAnswer.add(temp.number);
        		count++;
        	}else {
        		tempAnswer.add(temp.number);
        		count=1;
        		lastName=temp.genre;
        	}
        }
        answer = new int[tempAnswer.size()];
        for(int i=0; i<answer.length; i++) answer[i]=tempAnswer.get(i);
        return answer;
    }
}
