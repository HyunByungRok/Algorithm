package Programmers;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		Programmers_베스트앨범 p = new Programmers_베스트앨범();
		int[] answer = p.solution(genres, plays);
		System.out.println(Arrays.toString(answer));
	}
}
