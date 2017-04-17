package view;

import java.util.Scanner;

import model.Customer;

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
		// insert input later
		Customer c = new Customer("Peter", "Test", "Skolegade 1, 1000 Copenhagen", "h-test@gmail.com", "031090-1234");
		return c;
	}
}
