package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import data.FileManipulation;
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
	// creates objects of CustomerDatabase and BikeDatabase
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

	
	private void runProgram() {

		// shows login menu and lets user choice to register or login
		boolean correctInput = false;
		while (!correctInput) {
			int choice = welcome.loginMenu();
			correctInput = true;
			switch (choice) {
			case WelcomeView.MENUCHOICE_REGISTER:
				// adds newly registered customer
				currentCustomer = customerView.getCustomerDetails();
				customerDb.addCustomer(currentCustomer);
				System.out
						.println("\nHello, " + currentCustomer.getFirstName() + "! Let's get this journey started!\n");
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
				// go to AdminController
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
				correctInput = true;
				int choice = customerView.browseBikesMenu();
				switch (choice) {
				case CustomerView.MENUCHOICE_BIKES:
					customerChoseRegularBike();
					break;
				case CustomerView.MENUCHOICE_EBIKES:
					customerChoseEbike();
					break;
				case CustomerView.MENUCHOICE_RETURNBIKE:
					customerReturnBike();
					break;
				case CustomerView.MENUCHOICE_BOOKINGHISTORY:
					customerViewBookingHistory();
					break;
				case CustomerView.MENUCHOICE_EXIT:
					System.out.println("You have exited the program.");
					System.out.println("We hope to see you again soon!");
					browsingBikes = false;
					break;
				default:
					correctInput = false;
					System.out.println("Invalid input. Please type a valid option.");
				}
			}
		}
	}

	private void customerReturnBike() {
		ArrayList<Booking> currentCustomerBookings = bookingDb.getBookingsByCustomer(currentCustomer);
		if (currentCustomerBookings.size() > 0) {
			CustomerView.displayOpenCustomerBookings(currentCustomerBookings);
			System.out.println("\nPlease enter the Booking ID of the bike you would like to return:");
			String chosenBookingId = input.nextLine();
			Booking bookingChoice = bookingDb.getBookingByBookingId(chosenBookingId);
			if (bookingChoice == null) {
				System.out.println("Invalid input. Make sure to type a valid ID.\n");
			} else {
				// sets availability in booking database to true, sets return date
				FileManipulation.updateAvailability(bookingChoice.getBike(), true);
				DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
				Date date = new Date();
				String returnDate = dateFormat.format(date);
				bookingChoice.setReturnDate(returnDate);
				// updates all bookings in database
				FileManipulation.writeBookingList(bookingDb.getBookingList());
				System.out.println("You have returned --> " + bookingChoice.getBike() + " <-- now.\n");
				System.out.println("We hope you enjoyed your ride!\n");
				CustomerView.displayOpenCustomerBookings(currentCustomerBookings);
			}
		} else {
			System.out.println("\nYou don't have any open bookings.");

		}
	}

	private void customerViewBookingHistory() {
		ArrayList<Booking> currentCustomerBookings = bookingDb.getBookingsByCustomer(currentCustomer);
		if (currentCustomerBookings.size() > 0) {
			customerView.displayCustomerBookingHistory(currentCustomerBookings);
		} else {
			System.out.println("\nYou haven't had any bookings yet.");
		}
	}

	private void customerContinueBooking() {
		int daysBooked = customerView.chooseDays();
		//It stores all Booking info which will be recorded to file in
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
				cardView.getInvoice(currentCustomer, bikeChoice, currentBooking);
				break;
			case CustomerView.MENUCHOICE_BROWSE:
				// does nothing, just goes back to the other menu
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

	
	private boolean customerChoseEbike() {
		CustomerView.displayElectricBikes(bikeDb);
		System.out.println("\nPlease enter the ID of the bike you would like to book:");
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