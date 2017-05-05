package model;

import java.util.ArrayList;

import data.FileManipulation;

public class BikeDatabase {
	private static ArrayList<Bike> bikeList = new ArrayList<Bike>();
	private static ArrayList<Ebike> ebikeList = new ArrayList<Ebike>();

	public BikeDatabase() {
		getBikeList();
		getEbikeList();
	}

	// overloading: adds bike to array list "bike list"
	public void addBike(Bike newBike) {
		bikeList.add(newBike);
		FileManipulation.writeRegularBikeList(bikeList);
	}

	// overloading: adds ebike to array list "ebike list"
	public void addBike(Ebike newEbike) {
		ebikeList.add(newEbike);
		FileManipulation.writeElectricBikeList(ebikeList);
	}

	public ArrayList<Bike> getBikeList() {
		bikeList = FileManipulation.getBikeDatabase();
		return bikeList;
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

	public void removeRegularBike(Bike bikeToDelete) {
		bikeList.remove(bikeToDelete);
		FileManipulation.writeRegularBikeList(bikeList);
	}

	public void removeElectricBike(Bike bikeToDelete) {
		ebikeList.remove(bikeToDelete);
		FileManipulation.writeElectricBikeList(ebikeList);
	}

	/*
	 * Returns the next available ID. Therefor goes through bikeList and
	 * ebikeList to find the highest existing ID and increases by 1.
	 */
	public void generateNewBikeId(Bike addedBike) {
		ArrayList<Bike> allBikes = new ArrayList<Bike>();
		allBikes.addAll(bikeList);
		allBikes.addAll(ebikeList);
		int newBikeId = 0;
		for (Bike b : allBikes) {
			if (b.getId() >= newBikeId) {
				newBikeId = b.getId();
			}
			newBikeId += 1;
		}
		addedBike.setId(newBikeId);
	}
}
