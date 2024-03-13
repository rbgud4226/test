package 주소록db연결;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AddrMenu menu = new AddrMenu();
		menu.run(sc);
	}
}
