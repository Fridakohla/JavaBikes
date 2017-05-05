package controller;

import java.util.Scanner;

import model.Bike;
import model.BikeDatabase;
import model.Booking;
import model.BookingDatabase;
import model.Customer;
import model.CustomerDatabase;
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
	private AdminController adminController;

	private Customer currentCustomer;

	// View objects
	public CustomerView customerView;
	public WelcomeView welcome;
	public PaymentView cardView;

	public JavaBikesController() {
		customerDb = new CustomerDatabase();
		bikeDb = new BikeDatabase();
		bookingDb = new BookingDatabase();

		customerView = new CustomerView();
		welcome = new WelcomeView();
		cardView = new PaymentView();
		adminController = new AdminController();
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
				adminController.runAdmin();
				break;
			default:
				correctInput = false;
				System.out.println("Invalid input. Please type a valid option.");
			}
		}
	}

	private void customerMainMenu() {
		boolean browsingBikes = true;
		while (browsingBikes) {
			boolean correctInput = false;
			while (!correctInput) {
				int choice = customerView.browseBikesMenu();
				switch (choice) {
				case CustomerView.MENUCHOICE_BIKES:
					correctInput = true;
					customerChoseRegularBike();
					break;
				case CustomerView.MENUCHOICE_EBIKES:
					correctInput = true;
					customerChoseEbike();
					break;
				case CustomerView.MENUCHOICE_EXIT:
					System.out.println("You have exited the program.");
					correctInput = true;
					browsingBikes = false;
					break;
				default:
					System.out.println("Invalid input. Please type a valid option.");
				}
			}
		}
	}

	private void customerContinueBooking() {
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
				break;
			default:
				correctInput = false;
				System.out.println("Invalid input. Please type a valid option.");
			}
		}
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
		} else {
			customerContinueBooking();
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
			customerView.displayNotAvailable();
			return false;
		} else {
			customerContinueBooking();
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