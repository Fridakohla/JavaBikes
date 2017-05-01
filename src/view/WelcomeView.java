package view;

import java.util.Scanner;

import controller.JavaBikesController;
import model.Customer;
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

	public int chooseDays() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nFor how many days would you like to rent the bike?");
		int daysBooked = input.nextInt();
		System.out
				.println("\n---> You have chosen " + JavaBikesController.bikeChoice + " for " + daysBooked + " days.");
		System.out.println(
				"---> Your total would be " + JavaBikesController.bikeChoice.getPrice() * daysBooked + " DKK.");
		return daysBooked;
	}

	public int confirmBookingMenu() {
		int choice;
		Scanner input = new Scanner(System.in);
		System.out.println("\nChoose one of the following options:");
		System.out.println("|1| Confirm and proceed to payment.");
		System.out.println("|2| Discard booking and browse again.");
		System.out.println("|3| Quit program.\n");
		choice = input.nextInt();
		return choice;
	}

	public Customer login(CustomerDatabase customerDb) {
		String usernameInput = "";
		String passwordInput = "";
		Customer inputCustomer = null;

		for (int countTries = 1; countTries < 4 && inputCustomer == null; countTries++) {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter your username: ");
			usernameInput = input.nextLine();
			System.out.print("Enter your password: ");
			passwordInput = input.nextLine();
			inputCustomer = customerDb.checkLogin(usernameInput, passwordInput);
			if (inputCustomer != null) {
				return inputCustomer;
			} else {
				System.out.println("\nYou have entered the wrong username and/or password. \nYou have "
						+ (3 - countTries) + " tries left.");
			}
		}
		System.out.println("You have exceeded the number of tries. Please try again later.");
		return null;
	}
}
