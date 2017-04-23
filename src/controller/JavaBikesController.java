package controller;

import java.util.Scanner;

import model.Bike;
import model.BikeDatabase;
import model.Customer;
import model.CustomerDatabase;
import view.CustomerView;
import view.WelcomeView;

public class JavaBikesController {
	// create objects of CustomerDatabase and BikeDatabase
	CustomerDatabase customerDb;
	BikeDatabase bikeDb;
	CustomerView myView = new CustomerView();
	Customer myCustomer;

	/** Constructor (moved in front for better structure view) */
	public JavaBikesController() {
		// initializes db objects
		customerDb = new CustomerDatabase();
		bikeDb = new BikeDatabase();

		// TESTING WITH DEMO BIKE
		Bike testBike = new Bike(88, "white", "women", 50, true);
		bikeDb.addBike(testBike);
	}

	public static void main(String[] args) {
		// construct new controller object
		JavaBikesController controller = new JavaBikesController();
		controller.runProgram();
	}

	private void runProgram() {
		WelcomeView welcome = new WelcomeView(); // creates new object of
													// WelcomeView
		int choice = welcome.firstMenuChoice(); // user input, 1 to login, 2 to
		// register

		if (choice == WelcomeView.MENUCHOICE_REGISTER) {
			customerDb.addNewCustomer(); // adds newly registered customer

		} else if (choice == WelcomeView.MENUCHOICE_LOGIN) {
			if (welcome.login(customerDb)) {
				// needs to fill in: return booked bike or continue to book bike
				System.out.println("Your login was successful.\n");
				// continue with browse bikes
			} else {
				System.out.println("You have exited the program.");
			}
		}
		boolean correctInput = false;
		while (!correctInput) {
			choice = welcome.secondMenuChoice();
			switch (choice) {
			case WelcomeView.MENUCHOICE_BIKES:
				correctInput = true;
				displayBikes();
				break;
			case WelcomeView.MENUCHOICE_EBIKES:
				System.out.println("Browsing the ebikes.");
				correctInput = true;
				break;
			case WelcomeView.MENUCHOICE_EXIT:
				System.out.println("You have exited the program.");
				correctInput = true;
				break;
			default:
				System.out.println("Invalid input. Please type a valid option.");
			}
		}
	}

	// needs to go to a view class?
	private void displayBikes() {
		System.out.println("Here is a list of our bikes.\n");
		System.out.println("ID \t\tColor \t\tType \t\tPrice \t\tAvailable?");
		System.out.println("--------------------------------------------------------------------");
		for (Bike myBike : bikeDb.getBikeList()) {
			System.out.println(myBike.getId() + "\t\t" + myBike.getColor() + "\t\t" + myBike.getType() + "\t\t"
					+ myBike.getPrice() + " DKK\t\t" + myBike.isAvailable());
		}
		// needs to go to a seperate method? if argument needs to be improved
		System.out.println("\nPlease enter the ID of the bike you would like to book:");
		Scanner input = new Scanner(System.in);
		boolean correctInput = false;
		int bookingChoice = 0;
		while (!correctInput) {
			bookingChoice = input.nextInt();
			if (bookingChoice != 0 && bookingChoice <= 88) {
				// method needs to be created
				// Bike bikeChosen = BikeDatabase.getBikeById(bookingChoice);
				correctInput = true;
			}
		}
	}

}