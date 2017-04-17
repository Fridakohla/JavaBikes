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
		// insert input later
		Customer c = new Customer().setFirstName("Peter").setLastName("Test").setAddress("Skolegade 1, 1000 Copenhagen")
				.setEmail("h-test@gmail.com").setCpr("031090-1234").setUsername("Peter T.").setPassword("peter");
		return c;
	}

	public boolean login(CustomerDatabase customerDb) {
		// get user input - username and password, three tries?
		String username = "Hans T.";
		String password = "hansi";
		return customerDb.checkLogin(username, password);
	}
}
