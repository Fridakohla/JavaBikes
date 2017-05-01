package controller;

import java.util.Scanner;

import model.Bike;
import model.BikeDatabase;
import model.Booking;
import model.BookingDatabase;
import model.Customer;
import model.CustomerDatabase;
import view.AdminView;
import view.CustomerView;
import view.PaymentView;
import view.WelcomeView;

public class JavaBikesController {
	// create objects of CustomerDatabase and BikeDatabase
	public static CustomerDatabase customerDb;
	Customer currentCustomer;
	public static BikeDatabase bikeDb;
	CustomerView customerView = new CustomerView();
	WelcomeView welcome = new WelcomeView(); // creates new object of
												// WelcomeView
	PaymentView cardView = new PaymentView();
	AdminView adminView = new AdminView();

	public static Bike bikeChoice;
	public static Booking currentBooking = new Booking();

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
		int choice = welcome.loginMenu();

		while (!correctInput) {
			switch (choice) {
			case WelcomeView.MENUCHOICE_REGISTER:
				customerDb.addNewCustomer(); // adds newly registered customer
				correctInput = true;
				break;
			case WelcomeView.MENUCHOICE_LOGIN:
				currentCustomer = customerView.login(customerDb);
				if (currentCustomer != null) { // returns null when wrong
												// username
												// and/or
												// pw for too many times
					correctInput = true;
					System.out.println("\nHello, " + currentCustomer.getFirstName() + "! Your login was successful.\n");
					// needs to fill in: return booked bike or continue to book
					// bike
					// continue with browse bikes
				} else {
					correctInput = true;
					browsingBikes = false;
				}
				break;
			case WelcomeView.MENUCHOICE_ADMIN:
				runAdmin();
				correctInput = true;
				break;
			default:
				System.out.println("Invalid input. Please type a valid option.");
			}
		}

		while (browsingBikes) {
			continueBooking = getBrowseBikesMenu();
			if (continueBooking) {
				int daysBooked = customerView.chooseDays();
				// It stores all Booking info which will be recorded to file in
				// next step if confirmed.
				currentBooking.setBookingDetails(bikeChoice, currentCustomer, daysBooked);
				correctInput = false;
				while (!correctInput) {
					choice = customerView.confirmBookingMenu();
					switch (choice) {
					case CustomerView.MENUCHOICE_CONFIRM:
						BookingDatabase.addBooking(currentBooking); // Booking
																	// confirmed
																	// and
																	// recorder
																	// to file
						cardView.validateCreditCardDetails(currentCustomer);
						correctInput = true;
						browsingBikes = false;
						break;
					case CustomerView.MENUCHOICE_BROWSE:
						correctInput = true;
						break;
					case CustomerView.MENUCHOICE_EXIT:
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

	private void runAdmin() {
		boolean correctInput = false;
		// admin login check -- if false, program ends
		boolean adminContinue = adminView.adminLogin();
		while (adminContinue) {
			if (!correctInput) {
				Customer deletedCustomer;
				int choice = adminView.adminMainMenu();
				switch (choice) {
				case AdminView.MENUCHOICE_DELETECUSTOMER:
					adminView.displayCustomerList();
					deletedCustomer = deleteCustomerFromList();
					if (deletedCustomer != null) {
						customerDb.removeCustomer(deletedCustomer);
						adminView.displayCustomerList();
						correctInput = true;
					} else {
						correctInput = false;
					}
					break;
				case AdminView.MENUCHOICE_MANAGEBIKES:
					manageBikes();
					correctInput = true;
					break;
				// add and remove -- bikes or ebikes
				case AdminView.MENUCHOICE_VIEWBOOKINGS:
					System.out.println("Viewing bookings.");
					correctInput = true;
					break;
				case CustomerView.MENUCHOICE_EXIT:
					System.out.println("You have exited the program.");
					correctInput = true;
					adminContinue = false;
					break;
				default:
					System.out.println("Invalid input. Please type a valid option.");
				}
			}
		}
	}

	private void manageBikes() {
		boolean correctInput = false;
		int choice = adminView.manageBikesMenu();
		while (!correctInput) {
			switch (choice) {
			case AdminView.MENUCHOICE_REMOVEEBIKE:
				System.out.println("\nEnter the ID of the ebike you would like to delete from the database.");
				System.out.println("Type 'back' to return.");
				Scanner input = new Scanner(System.in);
				int deletedEbikeId = input.nextInt();
				// deletedEbike = BikeDatabase.getEbikeByID(deletedEbikeId);
			}
		}
	}

	public Customer deleteCustomerFromList() {
		boolean correctInput = false;
		while (!correctInput) {
			System.out.println(
					"\nPlease enter the user name of the customer you would like to delete from the database.");
			System.out.println("Type 'back' to return.");
			Scanner input = new Scanner(System.in);
			String deletedCustomer = input.nextLine();
			for (Customer c : customerDb.getCustomerList()) {
				if (c.getUsername().equals(deletedCustomer)) {
					correctInput = true;
					return c;
				} else if (deletedCustomer.equals("back")) {
					correctInput = true;
					return null;
					// go back to main menu
				}
			}
			System.out.println("\nInvalid input.");
		}
		return null;
	}

	public boolean getBrowseBikesMenu() {
		boolean correctInput = false;
		while (!correctInput) {
			int choice = customerView.browseBikesMenu();
			switch (choice) {
			case CustomerView.MENUCHOICE_BIKES:
				correctInput = true;
				int chosenBikeId = customerView.browseBikes();
				bikeChoice = BikeDatabase.getBikeByID(chosenBikeId);
				if (bikeChoice.isAvailable() == false) {
					System.out.println("Sorry, the bike you have chosen is currently not available.");
					System.out.println("Please come back another day or chose a different bike.\n");
					return false;
				}
				break;
			case CustomerView.MENUCHOICE_EBIKES:
				correctInput = true;
				chosenBikeId = customerView.browseElectricBikes();
				bikeChoice = BikeDatabase.getEbikeByID(chosenBikeId);
				break;
			case CustomerView.MENUCHOICE_EXIT:
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