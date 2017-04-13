package controller;

import model.Customer;
import model.CustomerDatabase;

public class JavaBikesController {
	CustomerDatabase customerDb;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JavaBikesController();

	}

	public JavaBikesController() {
		customerDb = new CustomerDatabase();
		addDemoData();
		System.out.println(customerDb.getCustomerList());
	}

	private void addDemoData() {
		// Customer
		Customer customer1 = new Customer("Hans", "Test", "Skolegade 1, 1000 Copenhagen", "h-test@gmail.com",
				"031090-1234");
		customerDb.addCustomer(customer1);

	}

}
