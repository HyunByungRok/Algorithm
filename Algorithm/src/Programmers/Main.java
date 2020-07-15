package Programmers;

public class Main {
	public static void main(String[] args) {
		String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
		Programmers_위장 p = new Programmers_위장();
		int answer = p.solution(clothes);
		System.out.println(answer);
	}
}
