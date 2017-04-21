package view;

import java.util.Scanner;

import model.CustomerDatabase;

// TEST TEST
public class WelcomeView {
	public static final int MENUCHOICE_LOGIN = 1;
	public static final int MENUCHOICE_REGISTER = 2;

	public int menuChoice() {
		int choice = 0;
		while (choice != 1 && choice != 2) {
			Scanner input = new Scanner(System.in);
			System.out.println("WELCOME TO JAVA BIKES!\n");
			System.out.println("Choose your option. 1 is login, 2 is register.");
			choice = input.nextInt();
			if (choice != 1 && choice != 2) {
				System.out.println("Invalid input.");
			}
		}
		return choice;
	}

	public boolean login(CustomerDatabase customerDb) {
		String usernameInput = "";
		String passwordInput = "";
		boolean correctInput = customerDb.checkLogin(usernameInput, passwordInput);

		for (int countTries = 1; countTries < 4 && !correctInput; countTries++) {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter your username: ");
			usernameInput = input.nextLine();
			System.out.print("Enter your password: ");
			passwordInput = input.nextLine();
			correctInput = customerDb.checkLogin(usernameInput, passwordInput);
			if (correctInput) {
				return correctInput;
			} else {
				System.out.println("\nYou have entered the wrong username and/or password. \nYou have "
						+ (3 - countTries) + " tries left.");
			}
		}
		return !correctInput;

	}
}
