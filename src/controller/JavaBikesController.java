package controller;

import model.Bike;
import model.BikeDatabase;
import model.Customer;
import model.CustomerDatabase;
import view.CustomerView;
import view.WelcomeView;

public class JavaBikesController {
	// create objects of CustomerDatabase and BikeDatabase
	CustomerDatabase customerDb;
	public static BikeDatabase bikeDb;
	CustomerView myView = new CustomerView();
	WelcomeView welcome = new WelcomeView(); // creates new object of
												// WelcomeView
	Customer myCustomer;
	public static Bike bookingChoice;

	/** Constructor (moved in front for better structure view) */
	public JavaBikesController() {
		// initializes db objects
		customerDb = new CustomerDatabase();
		bikeDb = new BikeDatabase();
	}

	public static void main(String[] args) {
		// construct new controller object
		JavaBikesController controller = new JavaBikesController();
		controller.runProgram();
	} // End of main

	private void runProgram() {
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

		boolean continueBooking = true;
		boolean correctInput = false;
		secondMenu();

		if (continueBooking) {
			correctInput = false;
			while (!correctInput) {
				choice = welcome.thirdMenuChoice();
				switch (choice) {
				case WelcomeView.MENUCHOICE_CONFIRM:
					correctInput = true;
					System.out.println("Confirmed booking.");
					break;
				case WelcomeView.MENUCHOICE_BROWSE:
					welcome.secondMenuChoice();
					correctInput = true;
					break;
				case WelcomeView.MENUCHOICE_EXIT:
					System.out.println("You have exited the program.");
					correctInput = true;
					continueBooking = false;
					break;
				default:
					System.out.println("Invalid input. Please type a valid option.");
				}
			}
		}
	}

	public void secondMenu() {
		boolean correctInput = false;
		while (!correctInput) {
			int choice = welcome.secondMenuChoice();
			switch (choice) {
			case WelcomeView.MENUCHOICE_BIKES:
				correctInput = true;
				int chosenBikeId = myView.browseBikes();
				bookingChoice = BikeDatabase.getBikeByID(chosenBikeId);
				// BookingDatabase.addToShoppingCard(bookingChoice); TEST TEST
				// TEST
				break;
			case WelcomeView.MENUCHOICE_EBIKES:
				correctInput = true;
				chosenBikeId = myView.browseElectricBikes();
				bookingChoice = BikeDatabase.getBikeByID(chosenBikeId);
				break;
			case WelcomeView.MENUCHOICE_EXIT:
				System.out.println("You have exited the program.");
				correctInput = true;
				boolean continueBooking = false;
				break;
			default:
				System.out.println("Invalid input. Please type a valid option.");
			}
		}
	}

}