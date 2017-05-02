package view;

import java.util.Scanner;

import data.FileManipulation;
import model.Customer;

public class AdminView {
	public static final int MENUCHOICE_DELETECUSTOMER = 1;
	public static final int MENUCHOICE_MANAGEBIKES = 2;
	public static final int MENUCHOICE_VIEWBOOKINGS = 3;
	public static final int MENUCHOICE_ADDEBIKE = 1;
	public static final int MENUCHOICE_REMOVEEBIKE = 2;
	public static final int MENUCHOICE_ADDBIKE = 3;
	public static final int MENUCHOICE_REMOVEBIKE = 4;

	private String adminUsername = "admin";
	private String adminPassword = "admin1";

	public boolean adminLogin() {
		for (int countTries = 1; countTries < 4; countTries++) {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter the username: ");
			String adminUsernameInput = input.nextLine();
			System.out.print("Enter the password: ");
			String adminPasswordInput = input.nextLine();
			if (adminUsernameInput.equals(adminUsername) && adminPasswordInput.equals(adminPassword)) {
				System.out.println("\nYou are now logged in as an admin.");
				return true;
			} else {
				System.out.println("\nYou have entered the wrong username and/or password. \nYou have "
						+ (3 - countTries) + " tries left.\n");
			}
		}
		System.out.println("You have exceeded the number of tries. Please try again later.");
		return false;
	}

	public int adminMainMenu() {
		int choice;
		Scanner input = new Scanner(System.in);
		System.out.println("\nChoose one of the following options.");
		System.out.println("|1| View customer list and delete customer.");
		System.out.println("|2| Manage bikes.");
		System.out.println("|3| View bookings.");
		System.out.println("|0| Quit program.\n");
		choice = input.nextInt();
		return choice;
	}

	public int manageBikesMenu() {
		int choice;
		Scanner input = new Scanner(System.in);
		System.out.println("\nWhat would you like to do?");
		System.out.println("|1| Add ebike");
		System.out.println("|2| Remove ebike");
		System.out.println("|3| Add regular bike");
		System.out.println("|4| Remove regular bike");
		System.out.println("|0| Quit program.\n");
		choice = input.nextInt();
		return choice;
	}

	public void displayCustomerList() {
		System.out.println("Here is a list of all customers.\n");
		System.out.println("Username \tFirst Name \tLast Name \tCPR \t\t\tEmail");
		System.out.println(
				"----------------------------------------------------------------------------------------------");
		for (Customer myCustomer : FileManipulation.getCustomerDatabase()) {
			System.out.println(myCustomer.getUsername() + "\t\t" + myCustomer.getFirstName() + "\t\t"
					+ myCustomer.getLastName() + "\t\t" + myCustomer.getCpr() + "\t\t" + myCustomer.getEmail());
		}
	}
}

// add, remove, set availability
