package model;

import java.util.ArrayList;

public class BookingDatabase {
	private ArrayList<Booking> bookingList = new ArrayList<Booking>();

	public void addBooking(Booking newBooking) {
		bookingList.add(newBooking);
	}
	
	public void addToShoppingCard(Bike BookingChoice) {
		//think about : how to store 1 booking before it confirmed/or paid by customer
		Bike ShoppingCard = BookingChoice; // 1 instance of Bike object celected by customer
		
		
	}

	public ArrayList<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(ArrayList<Booking> bookingList) {
		this.bookingList = bookingList;
	}
}
