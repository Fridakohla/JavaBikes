package controller;

import model.BikeDatabase;
import model.Customer;
import model.CustomerDatabase;
import view.CustomerView;
import view.WelcomeView;

public class JavaBikesController {
	// create objects of CustomerDatabase and BikeDatabase
	CustomerDatabase customerDb;
	BikeDatabase bikeDb;
	CustomerView myView = new CustomerView();
	Customer myCustomer;

	/** Constructor (moved in front for better structure view) */
	public JavaBikesController() {
		// initializes db objects
		customerDb = new CustomerDatabase();
		bikeDb = new BikeDatabase();
		// addDemoData();
	}

	public static void main(String[] args) {
		// construct new controller object
		JavaBikesController controller = new JavaBikesController();
		controller.runProgram();
	}

	private void runProgram() {
		WelcomeView welcome = new WelcomeView(); // creates new object of
													// WelcomeView
		int choice = welcome.firstMenuChoice(); // user input, 1 to login, 2 to
		// register

		if (choice == WelcomeView.MENUCHOICE_REGISTER) {
			customerDb.addCustomer(); // adds newly registered customer

		} else if (choice == WelcomeView.MENUCHOICE_LOGIN) {
			if (welcome.login(customerDb)) {
				// needs to fill in: return booked bike or continue to book bike
				System.out.println("Your login was successful.\n");
				// continue with browse bikes
			} else {
				System.out.println("You have exited the program.");
			}
		}
		boolean correctInput = false;
		while (!correctInput) {
			choice = welcome.secondMenuChoice();
			switch (choice) {
			case WelcomeView.MENUCHOICE_BIKES:
				System.out.println("Browsing the bikes.");
				correctInput = true;
				break;
			case WelcomeView.MENUCHOICE_EBIKES:
				System.out.println("Browsing the ebikes.");
				correctInput = true;
				break;
			case WelcomeView.MENUCHOICE_EXIT:
				System.out.println("You have exited the program.");
				correctInput = true;
				break;
			default:
				System.out.println("Invalid input. Please type a valid option.");
			}
		}
	}

	/**
	 * private void addDemoData() { // Customer Customer customer1 = new
	 * Customer("Hans", "Test", "Skolegade 1, 1000 Copenhagen",
	 * "h-test@gmail.com", "031090-1234"); customer1.setUsername("Hans T.");
	 * customer1.setPassword("hansi"); customerDb.addCustomer(customer1);
	 * 
	 * // Bikes Bike bike1 = new Bike("red", "M", 1, true);
	 * bikeDb.addBike(bike1);
	 * 
	 * Bike bike2 = new Bike("blue", "L", 2, true); bikeDb.addBike(bike2);
	 * 
	 * Ebike ebike1 = new Ebike("gray", "L", 3, true); bikeDb.addBike(ebike1); }
	 */

}
