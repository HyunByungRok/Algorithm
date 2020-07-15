package Programmers;

public class Main {
	public static void main(String[] args) {
		String[] phone_book = {"119", "97674223", "1195524421"};
		Programmers_전화번호목록 p = new Programmers_전화번호목록();
		boolean answer = p.solution(phone_book);
		System.out.println(answer);
	}
}
