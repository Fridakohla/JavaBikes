package model;

import java.util.ArrayList;

public class BookingDatabase {
	private ArrayList<Booking> bookingList = new ArrayList<Booking>();

	public void addBooking(Booking newBooking) {
		bookingList.add(newBooking);
	}

	public ArrayList<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(ArrayList<Booking> bookingList) {
		this.bookingList = bookingList;
	}
}
