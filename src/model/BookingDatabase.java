package model;

import java.util.ArrayList;

import data.FileManipulation;

public class BookingDatabase {
	
	private static ArrayList<Booking> bookingList = new ArrayList<Booking>();

	
		
	public static void addBooking(Booking newBooking) {
		bookingList.add(newBooking); //NOT NEEDED??
		String details = newBooking.getBookingId() + "; " + newBooking.getCustomerUsername() + ";" + newBooking.getBikeId() 
		+ ";" + newBooking.getBikeColor() + ";" + newBooking.getBikeType() + ";" + newBooking.getPrice() 
		+ ";" + newBooking.getBookedDays() + ";" + newBooking.getStartTimeMs();
		FileManipulation.WriteDetails("bookingDatabase.txt", details);
	}
	
	public void addToShoppingCard(Bike BookingChoice) { // TO BE DELETED??
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
