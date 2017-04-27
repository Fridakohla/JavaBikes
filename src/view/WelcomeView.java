package view;

import java.util.Scanner;

import controller.JavaBikesController;
import model.CustomerDatabase;

public class WelcomeView {
	public static final int MENUCHOICE_LOGIN = 1;
	public static final int MENUCHOICE_REGISTER = 2;
	public static final int MENUCHOICE_BIKES = 1;
	public static final int MENUCHOICE_EBIKES = 2;
	public static final int MENUCHOICE_EXIT = 3;
	public static final int MENUCHOICE_CONFIRM = 1;
	public static final int MENUCHOICE_BROWSE = 2;

	public int loginMenu() {
		int choice = 0;
		while (choice != 1 && choice != 2) {
			Scanner input = new Scanner(System.in);
			System.out.println("WELCOME TO JAVA BIKES!\n");
			System.out.println("-----------------------\n");
			System.out.println("Choose your option. 1 is login, 2 is register.");
			System.out.println("|1| Login as an existing customer.");
			System.out.println("|2| Register as a new customer.\n");
			choice = input.nextInt();
			if (choice != 1 && choice != 2) {
				System.out.println("Invalid input.");
			}
		}
		return choice;
	}

	public int browseBikesMenu() {
		int choice;
		Scanner input = new Scanner(System.in);
		System.out.println("You are now browsing the bike catalog. Choose one of the following options.");
		System.out.println("|1| Browse regular bikes.");
		System.out.println("|2| Browse electric bikes.");
		System.out.println("|3| Quit program.\n");
		choice = input.nextInt();
		return choice;
	}

	public int confirmBookingMenu() {
		int choice;
		Scanner input = new Scanner(System.in);
		System.out.println("You have chosen " + JavaBikesController.bookingChoice + ".");
		System.out.println("\n---> Choose one of the following options:");
		System.out.println("|1| Confirm and proceed to payment.");
		System.out.println("|2| Browse again.");
		System.out.println("|3| Quit program.\n");
		choice = input.nextInt();
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
