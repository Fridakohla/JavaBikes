package model;

import java.util.ArrayList;

public class BikeDatabase {
	private static ArrayList<Bike> bikeList = new ArrayList<Bike>();

	// needs to be written to file

	public void addBike(Bike newBike) {
		bikeList.add(newBike);
	}

	public ArrayList<Bike> getBikeList() {
		return bikeList;
	}

	public void setBikeList(ArrayList<Bike> bikeList) {
		this.bikeList = bikeList;
	}

	public static Bike getBikeByID(int bookingChoice) {
		for (Bike myBike : bikeList) {
			if (myBike.getId() == bookingChoice) {
				return myBike;
			}
		}
		return null;
	}
	// ebike part needs to be added when ebike lists exits
	/*
	 * for (Bike myEbike : ebikeList) { if (myEbike.getId() == bookingChoice) {
	 * return myEbike; } } return null;
	 */
}
