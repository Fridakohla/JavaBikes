package view;

import java.util.Scanner;

import controller.JavaBikesController;
import model.Bike;
import model.BikeDatabase;
import model.Customer;
import model.CustomerDatabase;
import model.Ebike;

public class CustomerView {
	public static final int MENUCHOICE_BIKES = 1;
	public static final int MENUCHOICE_EBIKES = 2;
	public static final int MENUCHOICE_CONFIRM = 1;
	public static final int MENUCHOICE_BROWSE = 2;
	public static final int MENUCHOICE_EXIT = 0;

	public CustomerView() {
	}

	public int browseBikesMenu() {
		int choice;
		Scanner input = new Scanner(System.in);
		System.out.println("You are now browsing the bike catalog. Choose one of the following options.");
		System.out.println("|1| Browse regular bikes.");
		System.out.println("|2| Browse electric bikes.");
		System.out.println("|0| Quit program.\n");
		choice = input.nextInt();
		return choice;
	}

	public int chooseDays() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nFor how many days would you like to rent the bike?");
		int daysBooked = input.nextInt();
		System.out
				.println("\n---> You have chosen " + JavaBikesController.bikeChoice + " for " + daysBooked + " days.");
		System.out.println(
				"---> Your total would be " + JavaBikesController.bikeChoice.getPrice() * daysBooked + " DKK.");
		return daysBooked;
	}

	public int confirmBookingMenu() {
		int choice;
		Scanner input = new Scanner(System.in);
		System.out.println("\nChoose one of the following options:");
		System.out.println("|1| Confirm and proceed to payment.");
		System.out.println("|2| Discard booking and browse again.");
		System.out.println("|0| Quit program.\n");
		choice = input.nextInt();
		return choice;
	}

	public Customer login(CustomerDatabase customerDb) {
		String usernameInput = "";
		String passwordInput = "";
		Customer inputCustomer = null;

		for (int countTries = 1; countTries < 4 && inputCustomer == null; countTries++) {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter your username: ");
			usernameInput = input.nextLine();
			System.out.print("Enter your password: ");
			passwordInput = input.nextLine();
			inputCustomer = customerDb.checkLogin(usernameInput, passwordInput);
			if (inputCustomer != null) {
				return inputCustomer;
			} else {
				System.out.println("\nYou have entered the wrong username and/or password. \nYou have "
						+ (3 - countTries) + " tries left.");
			}
		}
		System.out.println("You have exceeded the number of tries. Please try again later.");
		return null;
	}

	public Customer getCustomerDetails() { // Creates a new customer
		Scanner input = new Scanner(System.in);
		String userInput;
		Customer C = new Customer();

		System.out.print("Enter your first name: ");
		userInput = input.nextLine();
		C.setFirstName(userInput);
		System.out.print("Enter your last name: ");
		userInput = input.nextLine();
		C.setLastName(userInput);
		// Enter and validation of CPR
		boolean cprCorrect = false;
		while (!cprCorrect) {
			System.out.println(" Please enter the CPR xxxxxx-xxxx :");
			userInput = input.nextLine();
			if (userInput.matches("^(\\d{6}-?\\d{4})$")) {
				C.setCpr(userInput);
				cprCorrect = true;
			} else
				System.out.println("format of the CPR is wrong");
		}
		System.out.print("Enter your address: ");
		userInput = input.nextLine();
		C.setAddress(userInput);
		// Email validation
		boolean emailCorrect = false;
		while (!emailCorrect) {
			System.out.println("Please enter your email :");
			userInput = input.nextLine();
			if (userInput.matches("^.+@.+\\..+$")) {
				C.setEmail(userInput);
				emailCorrect = true;
			} else
				System.out.println("Email format is incorrect ");
		}

		C.setUsername();
		C.setPassword();
		System.out.println("Your username is " + C.getUsername() + " and your password is " + C.getPassword());

		return C;
	}

	public void printCustomerDetails(Customer C) {
		System.out.println(C.toString());
	}

	// could go to a seperate bookingView class
	// displays the bikes (only regular ones, not ebikes)
	public static void displayRegularBikes(BikeDatabase bikeDb) {
		System.out.println("\nHere is a list of our regular bikes.\n");
		System.out.println("ID \tColor \t\tType \t\tPrice \t\tAvailable?");
		System.out.println("--------------------------------------------------------------------");
		for (Bike myBike : bikeDb.getBikeList()) {
			String availabilityString;
			if (myBike.isAvailable() == false) {
				availabilityString = "no";
			} else {
				availabilityString = "yes";
			}
			System.out.println(myBike.getId() + "\t" + myBike.getColor() + "\t\t" + myBike.getType() + "\t\t"
					+ myBike.getPrice() + " DKK\t\t" + availabilityString);
		}
	}

	public static void displayElectricBikes(BikeDatabase bikeDb) {
		System.out.println("\nHere is a list of our electric bikes.\n");
		System.out.println("ID \tColor \t\tType \t\tPrice \t\tAvailable? \tBattery Duration ");
		System.out.println("-----------------------------------------------------------------------------------------");
		for (Ebike myBike : bikeDb.getEbikeList()) {
			String availabilityString;
			if (myBike.isAvailable() == false) {
				availabilityString = "no";
			} else {
				availabilityString = "yes";
			}
			System.out.println(
					myBike.getId() + "\t" + myBike.getColor() + "\t\t" + myBike.getType() + "\t\t" + myBike.getPrice()
							+ " DKK\t\t" + availabilityString + "\t\t" + myBike.getBatteryDuration() + " hours");
		}
	}

	public void displayNotAvailable() {
		System.out.println("Sorry, the bike you have chosen is currently not available.");
		System.out.println("Please come back another day or chose a different bike.\n");
	}

}
