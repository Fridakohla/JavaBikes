package view;

import java.util.Scanner;

import controller.JavaBikesController;
import model.Bike;
import model.BikeDatabase;
import model.Booking;
import model.BookingDatabase;
import model.Customer;
import model.CustomerDatabase;
import model.Ebike;

public class AdminView {
	static Scanner input = new Scanner(System.in);

	public static final int MENUCHOICE_DELETECUSTOMER = 1;
	public static final int MENUCHOICE_MANAGEBIKES = 2;
	public static final int MENUCHOICE_VIEWBOOKINGS = 3;
	public static final int MENUCHOICE_ADDEBIKE = 1;
	public static final int MENUCHOICE_REMOVEEBIKE = 2;
	public static final int MENUCHOICE_ADDBIKE = 3;
	public static final int MENUCHOICE_REMOVEBIKE = 4;
	public static final int MENUCHOICE_MAINMENU = 5;

	private String adminUsername = "admin";
	private String adminPassword = "admin1";

	public boolean adminLogin() {
		for (int countTries = 1; countTries < 4; countTries++) {
			System.out.print("Enter the username: ");
			String adminUsernameInput = input.nextLine();
			System.out.print("Enter the password: ");
			String adminPasswordInput = input.nextLine();
			if (adminUsernameInput.equals(adminUsername) && adminPasswordInput.equals(adminPassword)) {
				System.out.println("\nYou are now logged in as an admin.");
				return true;
			} else {
				System.out.println("\nYou have entered the wrong username and/or password. \nYou have "
						+ (3 - countTries) + " tries left.\n");
			}
		}
		System.out.println("You have exceeded the number of tries. Please try again later.");
		return false;
	}

	public int adminMainMenu() {
		int choice;
		System.out.println("\nChoose one of the following options.");
		System.out.println("|1| View customer list and delete customer.");
		System.out.println("|2| View bike list and manage bikes.");
		System.out.println("|3| View bookings.");
		System.out.println("|0| Quit program.\n");
		choice = input.nextInt();
		return choice;
	}

	public int manageBikesMenu() {
		int choice;
		System.out.println("\nWhat would you like to do?");
		System.out.println("|1| Add ebike");
		System.out.println("|2| Remove ebike");
		System.out.println("|3| Add regular bike");
		System.out.println("|4| Remove regular bike");
		System.out.println("|5| Go back to main menu.\n");
		choice = input.nextInt();
		return choice;
	}

	public void displayCustomerList(CustomerDatabase customerDb) {
		System.out.println("Here is a list of all customers.\n");
		System.out.println("Username \tFirst Name \tLast Name \tCPR \t\t\tEmail");
		System.out.println(
				"----------------------------------------------------------------------------------------------");
		for (Customer myCustomer : customerDb.getCustomerList()) {
			System.out.println(myCustomer.getUsername() + "\t\t" + myCustomer.getFirstName() + "\t\t"
					+ myCustomer.getLastName() + "\t\t" + myCustomer.getCpr() + "\t\t" + myCustomer.getEmail());
		}
	}

	public Customer selectCustomerFromList(CustomerDatabase customerDb) {
		Scanner input = new Scanner(System.in);
		displayCustomerList(customerDb);
		boolean correctInput = false;
		while (!correctInput) {
			System.out.println(
					"\nPlease enter the user name of the customer you would like to delete from the database.");
			System.out.println("Type 'back' to return.");
			String deletedCustomer = input.nextLine();
			if (deletedCustomer.equals("back")) {
				return null;
			}
			Customer customerToDelete = CustomerDatabase.getCustomerByUsername(deletedCustomer);
			if (customerToDelete == null) {
				System.out.println("\nInvalid input.");
			} else {
				return customerToDelete;
			}
		}
		return null;
	}

	public Bike selectRegularBikeFromList(BikeDatabase bikeDb) {
		CustomerView.displayRegularBikes(bikeDb);
		boolean correctInput = false;
		while (!correctInput) {
			System.out.println("\nEnter the ID of the bike you would like to delete from the database.");
			System.out.println("Type '0' to return.");
			int chosenBikeId = JavaBikesController.chooseBike();
			Bike bikeChoice = BikeDatabase.getBikeByID(chosenBikeId);
			if (chosenBikeId == 0) {
				correctInput = true;
				return null;
			} else if (bikeChoice == null) {
				System.out.println("Invalid input. Make sure to type a valid ID.\n");
			} else {
				correctInput = true;
				return bikeChoice;
			}
		}
		return null;
		// null goes back to main menu
	}

	public Bike selectElectricBikeFromList(BikeDatabase bikeDb) {
		CustomerView.displayElectricBikes(bikeDb);
		boolean correctInput = false;
		while (!correctInput) {
			System.out.println("\nEnter the ID of the bike you would like to delete from the database.");
			System.out.println("Type '0' to return.");
			int chosenBikeId = JavaBikesController.chooseBike();
			Bike bikeChoice = bikeDb.getEbikeByID(chosenBikeId);
			if (chosenBikeId == 0) {
				correctInput = true;
				return null;
			} else if (bikeChoice == null) {
				System.out.println("Invalid input. Make sure to type a valid ID.\n");
			} else {
				correctInput = true;
				return bikeChoice;
			}
		}
		return null;
		// null goes back to main menu
	}

	public void addRegularBike(Bike addedBike) {
		Scanner input = new Scanner(System.in);
		JavaBikesController.bikeDb.generateNewBikeId(addedBike);
		System.out.println("Enter the color of the bike: ");
		String adminInput = input.nextLine();
		addedBike.setColor(adminInput);
		System.out.println("Enter bike type (man, woman or child): ");
		adminInput = input.nextLine();
		addedBike.setType(adminInput);
		System.out.println("Enter the price of the bike: ");
		int adminInputPrice = input.nextInt();
		addedBike.setPrice(adminInputPrice);
		System.out.println("Enter the availability (true or false): ");
		boolean adminInputAvailability = input.nextBoolean();
		addedBike.setAvailable(adminInputAvailability);
	}

	public void addEbike(Ebike addedEbike) {
		// fill with general information
		addRegularBike(addedEbike);
		// fill with ebike specific information
		System.out.println("Enter the battery duration of the ebike: ");
		int adminInputBattery = input.nextInt();
		addedEbike.setBatteryDuration(adminInputBattery);
	}

	public void displayBookingList(BookingDatabase bookingDb) {
		System.out.println("\nHere is an overview of all bookings.\n");
		System.out.println("Booking ID \tUsername \tPrice \t\tBooked Days \tStart Time \t\tReturned?");
		System.out.println(
				"-------------------------------------------------------------------------------------------------");
		for (Booking myBooking : bookingDb.getBookingList()) {
			String returnedString;
			if (myBooking.getReturnDate() == null) {
				returnedString = "no";
			} else {
				returnedString = myBooking.getReturnDate();
			}
			try {
				System.out.println(myBooking.getBookingId() + "\t\t" + myBooking.getCustomer().getUsername() + "\t\t"
						+ myBooking.getPrice() + "\t\t" + myBooking.getBookedDays() + "\t\t" + myBooking.getStartTime()
						+ "\t" + returnedString);
			} catch (Exception e) {
				// if there is an exception then either the bike or the customer
				// does not exist anymore

			}

		}
	}
}

// add, remove, set availability
