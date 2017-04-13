package model;

import java.util.ArrayList;

public class BikeDatabase {
	private ArrayList<Bike> bikeList = new ArrayList<Bike>();

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
