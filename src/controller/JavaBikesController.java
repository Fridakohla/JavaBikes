package controller;

import java.util.Scanner;

import model.Bike;
import model.BikeDatabase;
import model.Booking;
import model.BookingDatabase;
import model.Customer;
import model.CustomerDatabase;
import model.Ebike;
import view.AdminView;
import view.CustomerView;
import view.PaymentView;
import view.WelcomeView;

public class JavaBikesController {
	Scanner input = new Scanner(System.in);
	// create objects of CustomerDatabase and BikeDatabase
	public static CustomerDatabase customerDb;
	public static BikeDatabase bikeDb;
	public static Bike bikeChoice;
	public static Booking currentBooking = new Booking();
	Customer currentCustomer;
	CustomerView customerView = new CustomerView();
	WelcomeView welcome = new WelcomeView(); // creates new object of
												// WelcomeView
	PaymentView cardView = new PaymentView();
	AdminView adminView = new AdminView();

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

	/*
	 * say what the method does
	 */
	private void runProgram() {
		boolean continueBooking = true;
		boolean correctInput = false;
		boolean browsingBikes = true;

		// show login menu and let user choose to register or login
		int choice = welcome.loginMenu();

		while (!correctInput) {
			correctInput = true;
			switch (choice) {
			case WelcomeView.MENUCHOICE_REGISTER:
				// adds newly registered customer
				customerDb.addNewCustomer();
				break;
			case WelcomeView.MENUCHOICE_LOGIN:
				currentCustomer = customerView.login(customerDb);
				if (currentCustomer != null) { // returns null when wrong
												// username
												// and/or
												// pw for too many times
					System.out.println("\nHello, " + currentCustomer.getFirstName() + "! Your login was successful.\n");
					// needs to fill in: return booked bike or continue to book
					// bike
					// continue with browse bikes
				} else {
					browsingBikes = false;
				}
				break;
			case WelcomeView.MENUCHOICE_ADMIN:
				runAdmin();
				break;
			default:
				correctInput = false;
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
					correctInput = true;
					choice = customerView.confirmBookingMenu();
					switch (choice) {
					case CustomerView.MENUCHOICE_CONFIRM:
						BookingDatabase.addBooking(currentBooking); // Booking
																	// confirmed
																	// and
																	// recorder
																	// to file
						cardView.validateCreditCardDetails(currentCustomer, bikeChoice);
						browsingBikes = false;
						break;
					case CustomerView.MENUCHOICE_BROWSE:
						break;
					case CustomerView.MENUCHOICE_EXIT:
						System.out.println("You have exited the program.");
						continueBooking = false;
						browsingBikes = false;
						break;
					default:
						correctInput = false;
						System.out.println("Invalid input. Please type a valid option.");
					}
				}
			}
		}
	}

	private void runAdmin() {
		// admin login check -- if false, program ends
		boolean adminContinue = adminView.adminLogin();
		while (adminContinue) {
			int choice = adminView.adminMainMenu();
			boolean correctInput = false;
			while (!correctInput) {
				correctInput = true;
				switch (choice) {
				case AdminView.MENUCHOICE_DELETECUSTOMER:
					adminDeleteCustomer();
					break;
				case AdminView.MENUCHOICE_MANAGEBIKES:
					if (manageBikes()) {
					}
					break;
				// add and remove -- bikes or ebikes
				case AdminView.MENUCHOICE_VIEWBOOKINGS:

					System.out.println("Viewing bookings.");
					break;
				case CustomerView.MENUCHOICE_EXIT:
					System.out.println("You have exited the program.");
					adminContinue = false;
					break;
				default:
					correctInput = false;
					System.out.println("Invalid input. Please type a valid option.");
				}
			}
		}
	}

	private boolean manageBikes() {
		boolean correctInput = false;
		CustomerView.displayRegularBikes();
		CustomerView.displayElectricBikes();
		while (!correctInput) {
			correctInput = true;
			int choice = adminView.manageBikesMenu();
			switch (choice) {
			case AdminView.MENUCHOICE_REMOVEBIKE:
				adminDeleteRegularBike();
				return true;
			case AdminView.MENUCHOICE_REMOVEEBIKE:
				adminDeleteElectricBike();
				return true;
			case AdminView.MENUCHOICE_ADDBIKE:
				adminAddRegularBike();
				return true;
			case AdminView.MENUCHOICE_ADDEBIKE:
				adminAddElectricBike();
				return true;
			default:
				correctInput = false;
				return false;
			}
		}
		return false;
	}

	private void adminAddRegularBike() {
		Bike addedBike = new Bike();
		adminView.addRegularBike(addedBike);
		bikeDb.addBike(addedBike);
		System.out.println("You have added --> " + addedBike + " <-- to the database.");
		CustomerView.displayRegularBikes();
	}

	private void adminAddElectricBike() {
		Ebike addedEbike = new Ebike();
		adminView.addRegularBike(addedEbike);
		bikeDb.addBike(addedEbike);
		System.out.println("You have added --> " + addedEbike + " <-- to the database.");
		CustomerView.displayElectricBikes();
	}

	private void adminDeleteRegularBike() {
		Bike deletedBike;
		deletedBike = adminView.selectRegularBikeFromList(bikeDb);
		if (deletedBike != null) {
			bikeDb.removeRegularBike(deletedBike);
			System.out.println("You have deleted --> " + deletedBike + " <-- from the database.");
			CustomerView.displayRegularBikes();
		}

	}

	private void adminDeleteElectricBike() {
		Bike deletedBike;
		deletedBike = adminView.selectElectricBikeFromList(bikeDb);
		if (deletedBike != null) {
			bikeDb.removeElectricBike(deletedBike);
			System.out.println("You have deleted --> " + deletedBike + " <-- from the database.");
			CustomerView.displayElectricBikes();
		}

	}

	private void adminDeleteCustomer() {
		Customer deletedCustomer;
		deletedCustomer = adminView.selectCustomerFromList(customerDb);
		if (deletedCustomer != null) {
			customerDb.removeCustomer(deletedCustomer);
			System.out.println("You have deleted --> " + deletedCustomer + " <-- from the customer database.\n");
			adminView.displayCustomerList();
		}
	}

	public boolean getBrowseBikesMenu() {
		boolean correctInput = false;
		while (!correctInput) {
			int choice = customerView.browseBikesMenu();
			switch (choice) {
			case CustomerView.MENUCHOICE_BIKES:
				correctInput = true;
				CustomerView.displayRegularBikes();
				System.out.println("\nPlease enter the ID of the bike you would like to book:");
				int chosenBikeId = chooseBike();
				bikeChoice = BikeDatabase.getBikeByID(chosenBikeId);
				if (bikeChoice == null) {
					System.out.println("Invalid input. Make sure to type a valid ID.\n");
					return false;
				} else if (bikeChoice.isAvailable() == false) {
					customerView.displayNotAvailable();
					return false;
				}
				break;
			case CustomerView.MENUCHOICE_EBIKES:
				correctInput = true;
				CustomerView.displayElectricBikes();
				chosenBikeId = chooseBike();
				bikeChoice = bikeDb.getEbikeByID(chosenBikeId);
				if (bikeChoice == null) {
					System.out.println("Invalid input. Make sure to type a valid ID.\n");
					return false;
				} else if (bikeChoice.isAvailable() == false) {
					customerView.displayNotAvailable();
					return false;
				}
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

	public static int chooseBike() {
		Scanner input = new Scanner(System.in);
		boolean correctInput = false;
		int bookingChoice = 0;
		while (!correctInput) {
			bookingChoice = input.nextInt();
			if (bookingChoice >= 0) {
				correctInput = true;
			}
		}
		return bookingChoice;
	}
}