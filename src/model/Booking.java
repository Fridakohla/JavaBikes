package model;

public class Booking {
	Bike bike;
	Customer customer;
	long startTimeMs = System.currentTimeMillis(); // figure out start and end
													// time
	long endTimeMs;

	public int getTimeUsedMinutes(long startTimeMs, long endTimeMs) {
		int timeInMinutes = (int) (startTimeMs - endTimeMs) / 1000 / 60;
		return timeInMinutes;
	}

	public double calculatePrice(int timeInMinutes, double pricePerMinute) {
		double priceOfBooking = pricePerMinute * timeInMinutes;
		return priceOfBooking;
	}
}
