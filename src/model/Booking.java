package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
	Bike bike;
	Customer customer;
	private String startTime, bookingId;
	private String returnDate;
	public int bookedDays;
	private int price;

	public Booking() {

	}

	public Booking(String bookingId, int price, int bookedDays, String startTime, String returnDate, Bike bike,
			Customer customer) {
		this.startTime = startTime;
		this.bookingId = bookingId;
		this.returnDate = returnDate;
		this.bookedDays = bookedDays;
		this.price = price;
		this.bike = bike;
		this.customer = customer;
	}

	public void setBookingDetails(Bike chosenBike, Customer currentCustomer, int bookedDays) {
		calculateAndSetStartTime();
		calculateAndSetBookingId();
		setCustomer(currentCustomer);
		setBike(chosenBike);
		setBookedDays(bookedDays);
		setReturnDate(null);
		setPrice(chosenBike.getPrice());
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void calculateAndSetStartTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm");
		Date date = new Date();
		this.startTime = dateFormat.format(date);
	}

	public int getTotalPrice() {
		return getPrice() * this.getBookedDays();
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public int getBookedDays() {
		return bookedDays;
	}

	public void setBookedDays(int bookedDays) {
		this.bookedDays = bookedDays;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public void calculateAndSetBookingId() {
		this.bookingId = startTime.substring(4, 5) + startTime.substring(0, 2) + startTime.substring(11, 13)
				+ startTime.substring(14, 16);
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String toFileString() {
		return getBookingId() + ";" + getCustomer().getUsername() + ";" + getBike().getId() + ";" + getPrice() + ";"
				+ getBookedDays() + ";" + getStartTime() + ";" + getReturnDate();
	}

}
