package model;

import java.util.ArrayList;

import data.FileManipulation;

public class BookingDatabase {

	private static ArrayList<Booking> bookingList = new ArrayList<Booking>();

	public BookingDatabase() {
		bookingList = FileManipulation.getBookingDatabase();
	}

	// add a booking to booking list (array list) and update external file
	// booking database
	public static void addBooking(Booking newBooking) {
		bookingList.add(newBooking);
		FileManipulation.writeBooking(newBooking);
	}

	public ArrayList<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(ArrayList<Booking> bookingList) {
		this.bookingList = bookingList;
	}

	/*
	 * searches booking database for bookings of specific customer and puts them
	 * to an array list
	 */
	public ArrayList<Booking> getBookingsByCustomer(Customer currentCustomer) {
		ArrayList<Booking> customerBookings = new ArrayList<Booking>();
		for (Booking myBooking : bookingList) {
			String usernameFromBooking = myBooking.getCustomer().getUsername();
			String usernameCurrentCustomer = currentCustomer.getUsername();
			if (usernameFromBooking.equals(usernameCurrentCustomer)) {
				customerBookings.add(myBooking);
			}
		}
		return customerBookings;
	}

	public Booking getBookingByBookingId(String bookingChoice) {
		for (Booking myBooking : getBookingList()) {
			if (myBooking.getBookingId().equals(bookingChoice)) {
				return myBooking;
			}
		}
		return null;
	}
}
