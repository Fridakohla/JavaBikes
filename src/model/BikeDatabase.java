package model;

import java.util.ArrayList;

import data.FileManipulation;

public class BikeDatabase {
	private static ArrayList<Bike> bikeList = new ArrayList<Bike>();
	private static ArrayList<Ebike> ebikeList = new ArrayList<Ebike>();

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

	public static ArrayList<Ebike> getEbikeList() {
		ebikeList = FileManipulation.getEbikeDatabase();
		return ebikeList;
	}

	public static Bike getBikeByID(int bookingChoice) {
		for (Bike myBike : bikeList) {
			if (myBike.getId() == bookingChoice) {
				return myBike;
			}
		}
		return null;
	}

	public static Ebike getEbikeByID(int bookingChoice) {
		for (Ebike myBike : getEbikeList()) {
			if (myBike.getId() == bookingChoice) {
				return myBike;
			}
		}
		return null;
	}
}
