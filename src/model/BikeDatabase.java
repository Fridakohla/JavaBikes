package model;

import java.util.ArrayList;

import data.FileManipulation;

public class BikeDatabase {
	private static ArrayList<Bike> bikeList = new ArrayList<Bike>();
	private static ArrayList<Ebike> EbikeList = new ArrayList<Ebike>();

	// needs to be written to file

	public void addBike(Bike newBike) {
		bikeList.add(newBike);
	}

	public ArrayList<Bike> getBikeList() {
		bikeList = FileManipulation.getBikeDatabase();
		return bikeList;
	}

	public void setBikeList(ArrayList<Bike> bikeList) {
		this.bikeList = bikeList;
	}
	
	public ArrayList<Ebike> getEbikeList() {
		EbikeList = FileManipulation.getEbikeDatabase();
		return EbikeList;
	}

	public static Bike getBikeByID(int bookingChoice) {
		for (Bike myBike : bikeList) {
			if (myBike.getId() == bookingChoice) {
				return myBike;
			}
		}
		return null;
	}
	// ebike part needs to be added when ebike lists exits >>> DONE!
	/*
	 * for (Bike myEbike : ebikeList) { if (myEbike.getId() == bookingChoice) {
	 * return myEbike; } } return null;
	 */
}
