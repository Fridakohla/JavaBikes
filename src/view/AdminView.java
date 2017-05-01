package view;

import java.util.Scanner;

import data.FileManipulation;
import model.Customer;

public class AdminView {
	public static final int MENUCHOICE_DELETECUSTOMER = 1;
	public static final int MENUCHOICE_MANAGEBIKES = 2;
	public static final int MENUCHOICE_VIEWBOOKINGS = 3;

	private String adminUsername = "admin";
	private String adminPassword = "admin1";

	public boolean adminLogin() {
		for (int countTries = 1; countTries < 4; countTries++) {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter your username: ");
			String adminUsernameInput = input.nextLine();
			System.out.print("Enter your password: ");
			String adminPasswordInput = input.nextLine();
			if (adminUsernameInput.equals(adminUsername) && adminPasswordInput.equals(adminPassword)) {
				return true;
			} else {
				System.out.println("\nYou have entered the wrong username and/or password. \nYou have "
						+ (3 - countTries) + " tries left.\n");
			}
		}
		System.out.println("You have exceeded the number of tries. Please try again later.");
		return false;
	}

	public int adminFirstMenu() {
		int choice;
		Scanner input = new Scanner(System.in);
		System.out.println("\nYou are now logged in as an admin. Choose one of the following options.");
		System.out.println("|1| View customer list and delete customer.");
		System.out.println("|2| Manage bikes.");
		System.out.println("|3| View bookings.");
		System.out.println("|0| Quit program.\n");
		choice = input.nextInt();
		return choice;
	}

	public void displayCustomerList() {
		System.out.println("Here is a list of all customers.\n");
		System.out.println("Username \tFirst Name \t\tLast Name \t\tCPR \t\tEmail");
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		for (Customer myCustomer : FileManipulation.getCustomerDatabase()) {
			System.out.println(myCustomer.getUsername() + "\t\t" + myCustomer.getFirstName() + "\t\t\t"
					+ myCustomer.getLastName() + "\t\t\t" + myCustomer.getCpr() + "\t\t" + myCustomer.getEmail());
		}
	}
}

// add, remove, set availability
