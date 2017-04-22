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
			input.close(); // closes the user input
			if (correctInput) {
				return correctInput;
			} else {
				System.out.println("\nYou have entered the wrong username and/or password. \nYou have "
						+ (3 - countTries) + " tries left.");
			}
		}
		return !correctInput;

	}
	public static void runProgram(CustomerDatabase customerDB) {
		WelcomeView welcome = new WelcomeView(); // creates new object of
													// WelcomeView
		int choice = welcome.menuChoice(); // user input, 1 to login, 2 to
											// register
		if (choice == WelcomeView.MENUCHOICE_REGISTER) {
			customerDB.addNewCustomer(); // method adds new customer (directly to text file)
		} 
		else if (choice == WelcomeView.MENUCHOICE_LOGIN) {
			if (welcome.login(customerDB)) {
				// needs to fill in: return booked bike or continue to book bike
				System.out.print("Login successful.");
				// continue with browse bikes
			} else {
				System.out.println("You have exited the program.");
			}
		}
	}
}
