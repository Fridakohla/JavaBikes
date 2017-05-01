package model;

import java.util.ArrayList;

import data.FileManipulation;
import view.CustomerView;

public class CustomerDatabase {
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private static CustomerView myView = new CustomerView();
	private static Customer myCustomer = new Customer();

	public CustomerDatabase() {
		getCustomerList();
	}

	// method for adding customer in customer lists (DIRECTLY TO TEXT LIST)
	public void addNewCustomer() {
		myCustomer = myView.getCustomerDetails();
		myCustomer.writetoFile(); // Moved customer registration and Write to
									// file here from main
	}

	// method extracts data from text file and stores in array list of objects
	public ArrayList<Customer> getCustomerList() {
		customerList = FileManipulation.getCustomerDatabase();
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}

	// method to check if login is correct
	public Customer checkLogin(String username, String password) {
		for (Customer c : customerList) {
			// username and password for one customer object must be identical
			// to return true
			// null for now to handle null exception error
			if (c.getUsername() != null && c.getPassword() != null && c.getUsername().equals(username)
					&& c.getPassword().equals(password)) {
				return c;
			}
		}
		return null;
	}

	// maybe better to go on username level instead of whole object
	public void removeCustomer(Customer customerToDelete) {
		customerList.remove(customerToDelete);
		FileManipulation.writeCustomerList(customerList);
	}

}
