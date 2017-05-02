package model;

import java.util.ArrayList;

import data.FileManipulation;

public class BookingDatabase {

	private static ArrayList<Booking> bookingList = new ArrayList<Booking>();
	public static String FILENAME_BOOKINGDB = "bookingDatabase.csv";

	public static void addBooking(Booking newBooking) {
		bookingList.add(newBooking); // THIS LINE NOT REALLY NEEDED??
		String details = newBooking.toString();
		FileManipulation.writeDetails("FILENAME_BOOKINGDB", details);
	}

	public ArrayList<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(ArrayList<Booking> bookingList) {
		this.bookingList = bookingList;
	}
}
