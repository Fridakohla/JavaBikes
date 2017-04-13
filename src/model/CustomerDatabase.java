package model;

import java.util.ArrayList;

public class CustomerDatabase {
	private ArrayList<Customer> customerList = new ArrayList<Customer>();

	// method for adding customer in customer lists
	public void addCustomer(Customer newCustomer) {
		customerList.add(newCustomer);
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}

}
