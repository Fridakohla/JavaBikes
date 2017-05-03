package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
	Bike bike;
	Customer customer;
	private long startTimeMs = System.currentTimeMillis(); // figure out start
															// and end time
	private String startTime, bookingId;
	private String returnDate;
	private long endTimeMs;
	public int bookedDays;
	private int BikeId;
	private String bikeColor, CustomerUsername, bikeType;
	private int price;

	public Booking() {

	}

	public void setBookingDetails(Bike ChosenBike, Customer currentCustomer, int bookedDays) {

		setStartTime();
		setBookingId();
		setCustomer(currentCustomer);
		setCustomerUsername(currentCustomer.getUsername());
		setBikeId(ChosenBike.getId());
		setBikeColor(ChosenBike.getColor());
		setBikeType(ChosenBike.getType());
		setPrice(ChosenBike.getPrice());
		setBookedDays(bookedDays);
		setReturnDate(null);

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

	public void setStartTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.startTime = dateFormat.format(date);
	}

	public String getCustomerUsername() {
		return CustomerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		CustomerUsername = customerUsername;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getBikeId() {
		return BikeId;
	}

	public void setBikeId(int bikeId) {
		BikeId = bikeId;
	}

	public String getBikeColor() {
		return bikeColor;
	}

	public void setBikeColor(String bikeColor) {
		this.bikeColor = bikeColor;
	}

	public String getBikeType() {
		return bikeType;
	}

	public void setBikeType(String bikeType) {
		this.bikeType = bikeType;
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

	public void setBookingId() {
		this.bookingId = startTime.substring(5, 7) + startTime.substring(8, 10) + startTime.substring(11, 13)
				+ startTime.substring(14, 16);
	}
	
	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		
		this.returnDate = returnDate;
	}

	/**public void setStartTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.startTime = dateFormat.format(date);
	*/
	
	// get days instead
	public int getTimeUsedMinutes(long startTimeMs, long endTimeMs) {
		int timeInMinutes = (int) (startTimeMs - endTimeMs) / 1000 / 60;
		return timeInMinutes;
	}

	public double calculatePrice(int timeInMinutes, double pricePerMinute) {
		double priceOfBooking = pricePerMinute * timeInMinutes;
		return priceOfBooking;
	}

	@Override
	public String toString() {
		return getBookingId() + "; " + getCustomerUsername() + ";" + getBikeId() + ";" + getBikeColor() + ";"
				+ getBikeType() + ";" + getPrice() + ";" + getBookedDays() + ";" + getStartTime() +
				";" + getReturnDate();
	}

}

/**
 * public Booking(String bookingId, String Username, String startTime, int
 * bookedDays, int bikeId, String bikeColor, String bikeType, int price) {
 * super(); this.startTime = startTime; this.bookedDays = bookedDays;
 * this.bookingId = bookingId; CustomerUsername = Username; BikeId = bikeId;
 * this.bikeColor = bikeColor; this.bikeType = bikeType; this.price = price; }
 */