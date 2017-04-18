package controller;

import model.Bike;
import model.BikeDatabase;
import model.Customer;
import model.CustomerDatabase;
import model.Ebike;
import view.CustomerView;
import view.WelcomeView;

public class JavaBikesController {
	// create objects of CustomerDatabase and BikeDatabase
	CustomerDatabase customerDb;
	BikeDatabase bikeDb;

	public static void main(String[] args) {
		// construct new controller object
		JavaBikesController controller = new JavaBikesController();
		controller.runProgram();
	}

	private void runProgram() {
		WelcomeView welcome = new WelcomeView(); // creates new object of
													// WelcomeView
		CustomerView customer = new CustomerView();
		int choice = welcome.menuChoice(); // user input, 1 to login, 2 to
											// register
		if (choice == WelcomeView.MENUCHOICE_REGISTER) {
			Customer c = customer.getCustomerDetails();
			customerDb.addCustomer(c); // adds registered customer to
										// customer database (Array
										// List)
			System.out.println(customerDb.getCustomerList()); // return all
																// customers to
																// check
																// if it works
		} else if (choice == WelcomeView.MENUCHOICE_LOGIN) {
			if (welcome.login(customerDb)) {
				// needs to fill in: return booked bike or continue to book bike
				System.out.print("Login successful");
			} else {
				System.out.println("You have exited the program."); // program
																	// ends
																	// after
																	// exceeding
																	// three
																	// login
																	// tries in
																	// WelcomeView
			}
		}
	}

	public JavaBikesController() {
		// initializes db objects
		customerDb = new CustomerDatabase();
		bikeDb = new BikeDatabase();
		addDemoData();
		System.out.println(customerDb.getCustomerList());
		System.out.println(bikeDb.getBikeList());
	}

	private void addDemoData() {
		// Customer
		Customer customer1 = new Customer("Hans", "Test", "Skolegade 1, 1000 Copenhagen", "h-test@gmail.com",
				"031090-1234");
		customer1.setUsername("Hans T.");
		customer1.setPassword("hansi");
		customerDb.addCustomer(customer1);

		// Bikes
		Bike bike1 = new Bike("red", "M", 1, true);
		bikeDb.addBike(bike1);

		Bike bike2 = new Bike("blue", "L", 2, true);
		bikeDb.addBike(bike2);

		Ebike ebike1 = new Ebike("gray", "L", 3, true);
		bikeDb.addBike(ebike1);
	}

}
