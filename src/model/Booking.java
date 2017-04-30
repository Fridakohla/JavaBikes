package model;

import java.text.*;
import java.util.Date;

public class Booking {
	Bike bike;
	Customer customer;
	private long startTimeMs =System.currentTimeMillis(); // figure out start and end time
	private String startTime, bookingId;
	private long endTimeMs;
	private int bookedDays;
	private int BikeId;
	private String bikeColor,CustomerUsername, bikeType;
	private int price;
	
	
	public Booking() {
		
	}
	
	
	public Booking getBookingDetails(Bike ChosenBike, Customer CurrentCustomer, int bookedDays) {
		Booking B = new Booking();
		B.setStartTime();
		B.setBookingId();
		B.setCustomerUsername(CurrentCustomer.getUsername());
		B.setBikeId(ChosenBike.getId());
		B.setBikeColor(ChosenBike.getColor());
		B.setBikeType(ChosenBike.getType());
		B.setPrice(ChosenBike.getPrice());
		B.setBookedDays(bookedDays);
	
		return B;
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
		this.bookingId = startTime.substring(5,7)+ startTime.substring(8,10) +
				startTime.substring(11,13) +startTime.substring(14,16);
	}

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
		return getBookingId() + "; " + getCustomerUsername() + ";" + getBikeId() + ";" + getBikeColor() 
		+ ";" + getBikeType() + ";" + getPrice() + ";" + getBookedDays() + ";" + getStartTime();
	}
	

}

/**public Booking(String bookingId, String Username, String startTime, int bookedDays, int bikeId, 
		String bikeColor, String bikeType, int price) {
	super();
	this.startTime = startTime;
	this.bookedDays = bookedDays;
	this.bookingId = bookingId;
	CustomerUsername = Username;
	BikeId = bikeId;
	this.bikeColor = bikeColor;
	this.bikeType = bikeType;
	this.price = price;
} */