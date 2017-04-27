package model;

import data.FileManipulation;

public class Customer {
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String cpr;
	private String username;
	private String password;
	private String creditCardNumber;
	private String creditCardExpiration;
	private String creditCardCvc;

	public Customer() {

	}

	public Customer(String myFirstName, String myLastName, String myAddress, String myEmail, String myCpr) {
		firstName = myFirstName;
		lastName = myLastName;
		address = myAddress;
		email = myEmail;
		cpr = myCpr;
		setUsername();
		setPassword();
	}

	@Override
	public String toString() {
		return "first name: " + firstName + " - last name:" + lastName + " - address: " + address + " - email: " + email
				+ " - cpr: " + cpr;
	}

	public String getFirstName() {
		return firstName;
	}

	// object instead of void to make it easy to read and set data for customer
	public Customer setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Customer setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Customer setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Customer setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getCpr() {
		return cpr;
	}

	public Customer setCpr(String cpr) {
		this.cpr = cpr;
		return this;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public Customer setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
		return this;
	}

	public String getCreditCardExpiration() {
		return creditCardExpiration;
	}

	public Customer setCreditCardExpiration(String creditCardExpiration) {
		this.creditCardExpiration = creditCardExpiration;
		return this;
	}

	public String getCreditCardCvc() {
		return creditCardCvc;
	}

	public Customer setCreditCardCvc(String creditCardCvc) {
		this.creditCardCvc = creditCardCvc;
		return this;
	}

	// added these functions
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername() {
		this.username = firstName.substring(0, 1) + lastName.substring(0, 3);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword() {
		this.password = lastName.substring(0, 3) + cpr.substring(7, 11);
	}

	public void writetoFile() {
		String details = lastName + "; " + firstName + ";" + username + ";" + password + ";" + address + ";" + cpr + ";"
				+ email + ";";
		FileManipulation.WriteDetails("customer.txt", details);

	}
}
