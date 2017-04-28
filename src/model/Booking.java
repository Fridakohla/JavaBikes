package model;

public class Booking {
	Bike bike;
	Customer customer;
	long startTimeMs; // figure out start and end
													// time
	long endTimeMs;
	int bookedDays;
	int bookingId,  BikeId;
	String bikeColor,CustomerUsername, bikeType;
	int price;
	
	public static int Id;
	
	public Booking() {
		
	}

	public Booking(int bookingId, String Username, long startTimeMs, int bookedDays, int bikeId, 
			String bikeColor, String bikeType, int price) {
		super();
		this.startTimeMs = startTimeMs;
		this.bookedDays = bookedDays;
		this.bookingId = bookingId;
		CustomerUsername = Username;
		BikeId = bikeId;
		this.bikeColor = bikeColor;
		this.bikeType = bikeType;
		this.price = price;
	}
	
	
	public static Booking getBookingDetails(Bike ChosenBike, Customer CurrentCustomer, int bookedDays) {
		Booking B = new Booking();
		Id++;
		B.setBookingId(Id);
		B.setCustomerUsername(CurrentCustomer.getUsername());
		B.setBikeId(ChosenBike.getId());
		B.setBikeColor(ChosenBike.getColor());
		B.setBikeType(ChosenBike.getType());
		B.setPrice(ChosenBike.getPrice());
		B.setBookedDays(bookedDays);
		B.setStartTimeMs();

		return B;
	}
	
	
	public long getStartTimeMs() {
		return startTimeMs;
	}

	public void setStartTimeMs() {
		this.startTimeMs = System.currentTimeMillis();
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

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
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
		return "Booking [startTimeMs=" + this.startTimeMs + ", bookedDays=" + this.bookedDays + ", bookingId=" + this.bookingId
				+ ", BikeId=" + this.BikeId + ", bikeColor=" + this.bikeColor + ", CustomerUsername=" + this.CustomerUsername
				+ ", bikeType=" + this.bikeType + ", price=" + this.price + "]";
	}
}
