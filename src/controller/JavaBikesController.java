package controller;

import model.Bike;
import model.BikeDatabase;
import model.Customer;
import model.CustomerDatabase;
import model.Ebike;
import view.WelcomeView;

public class JavaBikesController {
	CustomerDatabase customerDb;
	BikeDatabase bikeDb;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaBikesController controller = new JavaBikesController();
		controller.runProgram();
	}

	private void runProgram() {
		WelcomeView welcome = new WelcomeView();
		int choice = welcome.menuChoice();
		if (choice == WelcomeView.MENUCHOICE_REGISTER) {
			Customer customer = welcome.registerCustomer();
			customerDb.addCustomer(customer);
			System.out.println(customerDb.getCustomerList());
		} else if (choice == WelcomeView.MENUCHOICE_LOGIN) {
			if (welcome.login(customerDb)) {
				// return booked bike or book bike
				System.out.print("Login successful");
			} else {
				System.out.println("You have exited the program.");
			}
		}
	}

	public JavaBikesController() {
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
