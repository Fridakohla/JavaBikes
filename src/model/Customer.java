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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

}
