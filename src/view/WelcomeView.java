package view;

import java.util.Scanner;

import model.Customer;
import model.CustomerDatabase;

public class WelcomeView {
	public static final int MENUCHOICE_LOGIN = 1;
	public static final int MENUCHOICE_REGISTER = 2;

	public int menuChoice() {
		int choice = 0;
		while (choice != 1 && choice != 2) {
			Scanner input = new Scanner(System.in);
			System.out.println("Choose your option. 1 is login, 2 is register.");
			choice = input.nextInt();
			if (choice != 1 && choice != 2) {
				System.out.println("Invalid input.");
			}
		}
		return choice;
	}

	public Customer registerCustomer() {
		// insert input and validations here
		// the following is test data to check
		Customer c = new Customer().setFirstName("Peter").setLastName("Test").setAddress("Skolegade 1, 1000 Copenhagen")
				.setEmail("h-test@gmail.com").setCpr("031090-1234").setUsername("Peter T.").setPassword("peter");
		return c;
	}

	public boolean login(CustomerDatabase customerDb) {
		// insert user input and outprints for user: username and password
		// generated manually or automatically, three tries?

		String username = "Hans T.";
		String password = "hansi";

		// needs to have username and passwords in database before it could work
		/*
		 * String username = ""; String password = ""; boolean correctInput =
		 * customerDb.checkLogin(username, password);
		 * 
		 * while (!correctInput) { for (int countTries = 1; countTries < 4;
		 * countTries++) { Scanner input = new Scanner(System.in);
		 * System.out.print("Enter your username: "); username =
		 * input.nextLine(); System.out.print("Enter your password: "); password
		 * = input.nextLine(); if (!correctInput) { System.out.
		 * println("You have entered the wrong username and/or password."); }
		 * else { correctInput = true; } } }
		 */
		return customerDb.checkLogin(username, password);
	}
}
