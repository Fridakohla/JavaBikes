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
	Customer currentCustomer;
	public static BikeDatabase bikeDb;
	CustomerView myView = new CustomerView();
	WelcomeView welcome = new WelcomeView(); // creates new object of
												// WelcomeView
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
		boolean continueBooking = true;
		boolean correctInput = false;
		boolean browsingBikes = true;
		int choice = welcome.loginMenu(); // user input, 1 to login, 2 to
		// register

		if (choice == WelcomeView.MENUCHOICE_REGISTER) {
			customerDb.addNewCustomer(); // adds newly registered customer

		} else if (choice == WelcomeView.MENUCHOICE_LOGIN) {
			currentCustomer = welcome.login(customerDb);
			if (currentCustomer != null) {
				// needs to fill in: return booked bike or continue to book bike
				System.out.println("\nHello, " + currentCustomer.getFirstName() + "! Your login was successful.\n");
				// System.out.println(currentCustomer); // test if customer is
				// stored
				// continue with browse bikes
			} else {
				browsingBikes = false;
			}
		}
		int daysBooked = 0;
		while (browsingBikes) {
			continueBooking = getBrowseBikesMenu();
			if (continueBooking) {
				daysBooked = welcome.chooseDays();
				correctInput = false;
				while (!correctInput) {
					choice = welcome.confirmBookingMenu();
					switch (choice) {
					case WelcomeView.MENUCHOICE_CONFIRM:
						System.out.println("Confirmed booking.");
						correctInput = true;
						browsingBikes = false;
						break;
					case WelcomeView.MENUCHOICE_BROWSE:
						correctInput = true;
						break;
					case WelcomeView.MENUCHOICE_EXIT:
						System.out.println("You have exited the program.");
						correctInput = true;
						continueBooking = false;
						browsingBikes = false;
						break;
					default:
						System.out.println("Invalid input. Please type a valid option.");
					}
				}
			}
		}
	}

	public boolean getBrowseBikesMenu() {
		boolean correctInput = false;
		while (!correctInput) {
			int choice = welcome.browseBikesMenu();
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
				bookingChoice = BikeDatabase.getEbikeByID(chosenBikeId);
				break;
			case WelcomeView.MENUCHOICE_EXIT:
				System.out.println("You have exited the program.");
				correctInput = true;
				return false;
			default:
				System.out.println("Invalid input. Please type a valid option.");
			}
		}
		return true;
	}

}