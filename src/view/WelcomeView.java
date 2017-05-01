package view;

import java.util.Scanner;

public class WelcomeView {
	public static final int MENUCHOICE_LOGIN = 1;
	public static final int MENUCHOICE_REGISTER = 2;
	public static final int MENUCHOICE_ADMIN = 3;

	public int loginMenu() {
		int choice = 0;
		while (choice != 1 && choice != 2 && choice != 3) {
			Scanner input = new Scanner(System.in);
			System.out.println("WELCOME TO JAVA BIKES!\n");
			System.out.println("-----------------------\n");
			System.out.println("Choose your option. 1 is login, 2 is register.");
			System.out.println("|1| Login as an existing customer.");
			System.out.println("|2| Register as a new customer.");
			System.out.println("|3| Login as admin.\n");
			choice = input.nextInt();
			if (choice != 1 && choice != 2 && choice != 3) {
				System.out.println("Invalid input.");
			}
		}
		return choice;
	}
}
