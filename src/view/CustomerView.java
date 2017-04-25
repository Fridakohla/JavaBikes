package view;

import java.util.Scanner;

import controller.JavaBikesController;
import data.FileManipulation;
import model.Bike;
import model.Customer;
import model.Ebike;

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

	// could go to a seperate bookingView class
	// displays the bikes (only regular ones, not ebikes) and lets the user
	// choose one by entering ID
	public int browseBikes() {
		System.out.println("Here is a list of our bikes.\n");
		System.out.println("ID \t\tColor \t\tType \t\tPrice \t\tAvailable?");
		System.out.println("--------------------------------------------------------------------");
		for (Bike myBike : JavaBikesController.bikeDb.getBikeList()) {
			System.out.println(myBike.getId() + "\t\t" + myBike.getColor() + "\t\t" + myBike.getType() + "\t\t"
					+ myBike.getPrice() + " DKK\t\t" + myBike.isAvailable());
		}
		System.out.println("\nPlease enter the ID of the bike you would like to book:");
		Scanner input = new Scanner(System.in);
		boolean correctInput = false;
		int bookingChoice = 0;
		while (!correctInput) {
			bookingChoice = input.nextInt();
			// if argument needs to be improved
			if (bookingChoice != 0 && bookingChoice <= 88) {
				correctInput = true;
			}
		}
		return bookingChoice;
	}

	////// TESTING -
	/**
	 * THIS ONE JUST TO TEST THE WORK OF EXTRATION FOR EBIKES - CAN DELETE OR
	 * USE IN THE RIGHT PLACE
	 */
	public int browseElectricBikes() {
		System.out.println("Here is a list of our bikes.\n");
		System.out.println("ID \tColor \t\tType \t\tPrice \t\tAvailable? \tDuration ");
		System.out.println("--------------------------------------------------------------------");
		for (Ebike myBike : FileManipulation.getEbikeDatabase()) {
			System.out.println(
					myBike.getId() + "\t" + myBike.getColor() + "\t\t" + myBike.getType() + "\t\t" + myBike.getPrice()
							+ " DKK\t\t" + myBike.isAvailable() + "\t\t" + myBike.getBatteryDuration() + " hours");
		}
		System.out.println("\nPlease enter the ID of the bike you would like to book:");
		Scanner input = new Scanner(System.in);
		boolean correctInput = false;
		int bookingChoice = 0;
		while (!correctInput) {
			bookingChoice = input.nextInt();
			// if argument needs to be improved
			if (bookingChoice != 0 && bookingChoice <= 88) {
				correctInput = true;
			}
		}
		return bookingChoice;
	}
}
