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
	Scanner input = new Scanner(System.in);
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

	/*
	 * say what the method does
	 * 
	 * @param
	 */
	private void runProgram() {
		boolean continueBooking = true;
		boolean correctInput = false;
		boolean browsingBikes = true;

		// show login menu and let user choose to register or login
		int choice = welcome.loginMenu();

		while (!correctInput) {
			switch (choice) {
			case WelcomeView.MENUCHOICE_REGISTER:
				// adds newly registered customer
				customerDb.addNewCustomer();
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
						cardView.validateCreditCardDetails(currentCustomer, bikeChoice);
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
			int choice = adminView.adminMainMenu();
			while (!correctInput) {
				Customer deletedCustomer;
				switch (choice) {
				case AdminView.MENUCHOICE_DELETECUSTOMER:
					adminView.displayCustomerList();
					deletedCustomer = deleteCustomerFromList();
					if (deletedCustomer == null) {
						correctInput = true;
					} else {
						customerDb.removeCustomer(deletedCustomer);
						System.out.println(
								"You have deleted --> " + deletedCustomer + " <-- from the customer database.\n");
						adminView.displayCustomerList();
						correctInput = true;
					}
					break;
				case AdminView.MENUCHOICE_MANAGEBIKES:
					if (manageBikes()) {
						correctInput = true;
					}
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

	private boolean manageBikes() {
		boolean correctInput = false;
		while (!correctInput) {
			int choice = adminView.manageBikesMenu();
			switch (choice) {
			case AdminView.MENUCHOICE_REMOVEBIKE:
				customerView.displayRegularBikes();
				deleteBikeFromList();
				correctInput = true;
				return true;
			}
		}
		return false;
	}

	private void deleteBikeFromList() {
		boolean correctInput = false;

		while (!correctInput) {
			System.out.println("\nEnter the ID of the bike you would like to delete from the database.");
			System.out.println("Type '0' to return.");
			int chosenBikeId = chooseBike();
			bikeChoice = BikeDatabase.getBikeByID(chosenBikeId);
			if (chosenBikeId == 0) {
				correctInput = true;
				// return false;
				// FEHLER
			} else if (bikeChoice == null) {
				System.out.println("Invalid input. Make sure to type a valid ID.\n");
			} else {
				bikeDb.removeBike(bikeChoice);
				System.out.println("You have deleted " + bikeChoice + " from the database.");
				customerView.displayRegularBikes();
			}
		}
		// return true;
	}

	public Customer deleteCustomerFromList() {
		boolean correctInput = false;
		while (!correctInput) {
			System.out.println(
					"\nPlease enter the user name of the customer you would like to delete from the database.");
			System.out.println("Type 'back' to return.");
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
				customerView.displayRegularBikes();
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
				customerView.displayElectricBikes();
				chosenBikeId = chooseBike();
				bikeChoice = BikeDatabase.getEbikeByID(chosenBikeId);
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

	public int chooseBike() {
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