package model;

import java.util.ArrayList;

public class BikeDatabase {
	private ArrayList<Bike> bikeList = new ArrayList<Bike>();

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
}
