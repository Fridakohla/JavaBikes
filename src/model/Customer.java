package model;

public class Customer {
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String cpr;

	public Customer() {

	}

	public Customer(String myFirstName, String myLastName, String myAddress, String myEmail, String myCpr) {
		firstName = myFirstName;
		lastName = myLastName;
		address = myAddress;
		email = myEmail;
		cpr = myCpr;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
