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
	public static BookingDatabase bookingDb;

	public static Bike bikeChoice;
	public static Booking currentBooking = new Booking();

	private Customer currentCustomer;

	// View objects
	public CustomerView customerView;
	public WelcomeView welcome;
	public PaymentView cardView;
	public AdminView adminView;

	public JavaBikesController() {
		customerDb = new CustomerDatabase();
		bikeDb = new BikeDatabase();
		bookingDb = new BookingDatabase();

		customerView = new CustomerView();
		welcome = new WelcomeView();
		cardView = new PaymentView();
		adminView = new AdminView();
	}

	public static void main(String[] args) {
		JavaBikesController controller = new JavaBikesController();
		controller.runProgram();
	} // End of main

	/*
	 * say what the method does
	 */
	private void runProgram() {

		// show login menu and let user choose to register or login
		boolean correctInput = false;
		while (!correctInput) {
			int choice = welcome.loginMenu();
			correctInput = true;
			switch (choice) {
			case WelcomeView.MENUCHOICE_REGISTER:
				// adds newly registered customer
				currentCustomer = customerView.getCustomerDetails();
				customerDb.addCustomer(currentCustomer);
				customerMainMenu();
				break;
			case WelcomeView.MENUCHOICE_LOGIN:
				currentCustomer = customerView.login(customerDb);
				// returns null when wrong username and/or pw for too many times
				if (currentCustomer != null) {
					System.out.println("\nHello, " + currentCustomer.getFirstName() + "! Your login was successful.\n");
					// continue with browse bikes
					customerMainMenu();
				} else {
					// Too many failed login tries. Program ends.
					System.out.println("You exited the program.");
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
	}

	private void runAdmin() {
		// admin login check -- if false, program ends
		boolean adminContinue = adminView.adminLogin();
		while (adminContinue) {
			boolean correctInput = false;
			while (!correctInput) {
				int choice = adminView.adminMainMenu();
				correctInput = true;
				switch (choice) {
				case AdminView.MENUCHOICE_DELETECUSTOMER:
					adminDeleteCustomer();
					break;
				case AdminView.MENUCHOICE_MANAGEBIKES:
					// add and remove -- bikes or ebikes
					adminManageBikes();
					break;
				case AdminView.MENUCHOICE_VIEWBOOKINGS:
					adminView.displayBookingList(bookingDb);
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

	private void adminManageBikes() {
		boolean correctInput = false;
		CustomerView.displayRegularBikes(bikeDb);
		CustomerView.displayElectricBikes(bikeDb);
		while (!correctInput) {
			correctInput = true;
			int choice = adminView.manageBikesMenu();
			switch (choice) {
			case AdminView.MENUCHOICE_REMOVEBIKE:
				adminDeleteRegularBike();
				break;
			case AdminView.MENUCHOICE_REMOVEEBIKE:
				adminDeleteElectricBike();
				break;
			case AdminView.MENUCHOICE_ADDBIKE:
				adminAddRegularBike();
				break;
			case AdminView.MENUCHOICE_ADDEBIKE:
				adminAddElectricBike();
				break;
			default:
				correctInput = false;
			}
		}
	}

	private void adminAddRegularBike() {
		Bike addedBike = new Bike();
		adminView.addRegularBike(addedBike);
		bikeDb.addBike(addedBike);
		System.out.println("You have added --> " + addedBike + " <-- to the database.");
		CustomerView.displayRegularBikes(bikeDb);
	}

	private void adminAddElectricBike() {
		Ebike addedEbike = new Ebike();
		adminView.addRegularBike(addedEbike);
		bikeDb.addBike(addedEbike);
		System.out.println("You have added --> " + addedEbike + " <-- to the database.");
		CustomerView.displayElectricBikes(bikeDb);
	}

	private void adminDeleteRegularBike() {
		Bike deletedBike;
		deletedBike = adminView.selectRegularBikeFromList(bikeDb);
		if (deletedBike != null) {
			bikeDb.removeRegularBike(deletedBike);
			System.out.println("You have deleted --> " + deletedBike + " <-- from the database.");
			CustomerView.displayRegularBikes(bikeDb);
		}

	}

	private void adminDeleteElectricBike() {
		Bike deletedBike;
		deletedBike = adminView.selectElectricBikeFromList(bikeDb);
		if (deletedBike != null) {
			bikeDb.removeElectricBike(deletedBike);
			System.out.println("You have deleted --> " + deletedBike + " <-- from the database.");
			CustomerView.displayElectricBikes(bikeDb);
		}

	}

	private void adminDeleteCustomer() {
		Customer deletedCustomer;
		deletedCustomer = adminView.selectCustomerFromList(customerDb);
		if (deletedCustomer != null) {
			customerDb.removeCustomer(deletedCustomer);
			System.out.println("You have deleted --> " + deletedCustomer + " <-- from the customer database.\n");
			adminView.displayCustomerList(customerDb);
		}
	}

	private void customerMainMenu() {
		boolean browsingBikes = true;
		while (browsingBikes) {
			boolean continueBooking = customerGetBrowseBikesMenu();
			if (continueBooking) {
				int daysBooked = customerView.chooseDays();
				// It stores all Booking info which will be recorded to file in
				// next step if confirmed.
				currentBooking.setBookingDetails(bikeChoice, currentCustomer, daysBooked);
				boolean correctInput = false;
				while (!correctInput) {
					correctInput = true;
					int choice = customerView.confirmBookingMenu();
					switch (choice) {
					case CustomerView.MENUCHOICE_CONFIRM:
						// Booking confirmed and recorder to file
						BookingDatabase.addBooking(currentBooking);
						cardView.validateCreditCardDetails(currentCustomer, bikeChoice);
						break;
					case CustomerView.MENUCHOICE_BROWSE:
						// do nothing, just go back to the other menu
						break;
					case CustomerView.MENUCHOICE_EXIT:
						System.out.println("You have exited the program!!");
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

	private boolean customerGetBrowseBikesMenu() {
		boolean correctInput = false;
		while (!correctInput) {
			int choice = customerView.browseBikesMenu();
			switch (choice) {
			case CustomerView.MENUCHOICE_BIKES:
				correctInput = true;
				boolean continueBooking = customerChoseRegularBike();
				return continueBooking;
			case CustomerView.MENUCHOICE_EBIKES:
				correctInput = true;
				continueBooking = customerChoseEbike();
				return continueBooking;
			case CustomerView.MENUCHOICE_EXIT:
				System.out.println("You have exited the program.");
				System.exit(0);
			default:
				System.out.println("Invalid input. Please type a valid option.");
			}
		}
		return true;
	}

	/**
	 * @return
	 */
	private boolean customerChoseEbike() {
		CustomerView.displayElectricBikes(bikeDb);
		int chosenBikeId = chooseBike();
		bikeChoice = bikeDb.getEbikeByID(chosenBikeId);
		if (bikeChoice == null) {
			System.out.println("Invalid input. Make sure to type a valid ID.\n");
			return false;
		} else if (bikeChoice.isAvailable() == false) {
			customerView.displayNotAvailable();
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	private boolean customerChoseRegularBike() {
		CustomerView.displayRegularBikes(bikeDb);
		System.out.println("\nPlease enter the ID of the bike you would like to book:");
		int chosenBikeId = chooseBike();
		bikeChoice = BikeDatabase.getBikeByID(chosenBikeId);
		if (bikeChoice == null) {
			System.out.println("Invalid input. Make sure to type a valid ID.\n");
			return false;
		} else if (bikeChoice.isAvailable() == false) {
			JavaBikesController.bikeChoice = bikeChoice;
			customerView.displayNotAvailable();
			return false;
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