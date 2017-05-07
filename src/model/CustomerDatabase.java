package model;

import java.util.ArrayList;

import data.FileManipulation;

public class CustomerDatabase {
	static ArrayList<Customer> customerList = new ArrayList<Customer>();

	public CustomerDatabase() {
		getCustomerList();
	}

	/* method for adding customer in customer lists */
	public void addCustomer(Customer newCustomer) {
		FileManipulation.writeCustomer(newCustomer);
	}

	/*
	 * method extracts data from text file and stores in array list of objects
	 */
	public static ArrayList<Customer> getCustomerList() {
		customerList = FileManipulation.getCustomerDatabase();
		return customerList;
	}

	/* method to check if login is correct */
	public Customer checkLogin(String username, String password) {
		for (Customer c : customerList) {
			// username and password for one customer object must be identical
			// to return true null for now to handle null exception error
			if (c.getUsername() != null && c.getPassword() != null && c.getUsername().equals(username)
					&& c.getPassword().equals(password)) {
				return c;
			}
		}
		return null;
	}

	// search for customer in customer list (by username)
	public static Customer getCustomerByUsername(String username) {
		getCustomerList();
		for (Customer c : customerList) {
			if (c.getUsername().equals(username)) {
				return c;
			}
		}
		return null;
	}

	// method to remove customer; removes from array list and rewrites external
	// file customer database
	public void removeCustomer(Customer customerToDelete) {
		customerList.remove(customerToDelete);
		FileManipulation.writeCustomerList(customerList);
	}

}
