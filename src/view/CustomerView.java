package view;

import java.util.Scanner;

import model.Customer;

public class CustomerView {

	public CustomerView() {
	}

	public Customer getCustomerDetails() { // Creates a new customer
		Scanner input = new Scanner(System.in);
		String userInput;
		Customer C = new Customer();

		System.out.print("Enter your first name: ");
		userInput = input.nextLine();
		C.setFirstName(userInput);
		System.out.print("Enter your last name: ");
		userInput = input.nextLine();
		C.setLastName(userInput);
		// Enter and validation of CPR
		boolean cprCorrect = false;
		while (!cprCorrect) {
			System.out.println(" Please enter the CPR xxxxxx-xxxx :");
			userInput = input.nextLine();
			if (userInput.matches("^(\\d{6}-?\\d{4})$")) {
				C.setCpr(userInput);
				cprCorrect = true;
			} else
				System.out.println("format of the CPR is wrong");
		}
		System.out.print("Enter your address: ");
		userInput = input.nextLine();
		C.setAddress(userInput);
		// Email validation
		boolean emailCorrect = false;
		while (!emailCorrect) {
			System.out.println("Please enter your email :");
			userInput = input.nextLine();
			if (userInput.matches("^.+@.+\\..+$")) {
				C.setEmail(userInput);
				emailCorrect = true;
			} else
				System.out.println("Email format is incorrect ");
		}
		C.setUsername();
		C.setPassword();
		System.out.println("Your username is " + C.getUsername() + " and your password is " + C.getPassword());

		return C;
	}

	public void printCustomerDetails(Customer C) {
		System.out.println(C.toString());
	}
}
