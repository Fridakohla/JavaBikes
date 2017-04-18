package view;

import java.util.Scanner;

import model.CustomerDatabase;

public class WelcomeView {
	public static final int MENUCHOICE_LOGIN = 1;
	public static final int MENUCHOICE_REGISTER = 2;

	public int menuChoice() {
		int choice = 0;
		while (choice != 1 && choice != 2) {
			Scanner input = new Scanner(System.in);
			// WELCOME Message
			System.out.println("Choose your option. 1 is login, 2 is register.");
			choice = input.nextInt();
			if (choice != 1 && choice != 2) {
				System.out.println("Invalid input.");
			}
		}
		return choice;
	}

	public boolean login(CustomerDatabase customerDb) {
		// insert user input and outprints for user: username and password
		// needs to have username and passwords in database before it could work

		String usernameInput = "";
		String passwordInput = "";
		boolean correctInput = customerDb.checkLogin(usernameInput, passwordInput);

		while (!correctInput) {
			for (int countTries = 1; countTries < 4; countTries++) {
				Scanner input = new Scanner(System.in);
				System.out.print("Enter your username: ");
				usernameInput = input.nextLine();
				System.out.print("Enter your password: ");
				passwordInput = input.nextLine();
				if (!correctInput) {
					System.out.println("You have entered the wrong username and/or password.");
				} else {
					correctInput = true;
				}
			}
		}

		return customerDb.checkLogin(usernameInput, passwordInput);
	}
}
