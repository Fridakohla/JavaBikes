package model;

import java.util.ArrayList;

import data.FileManipulation;

public class BookingDatabase {

	private static ArrayList<Booking> bookingList = new ArrayList<Booking>();

	public BookingDatabase() {
		bookingList = FileManipulation.getBookingDatabase();
	}

	public static void addBooking(Booking newBooking) {
		bookingList.add(newBooking); // THIS LINE NOT REALLY NEEDED??
		FileManipulation.writeBooking(newBooking);
	}

	public ArrayList<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(ArrayList<Booking> bookingList) {
		this.bookingList = bookingList;
	}
}
