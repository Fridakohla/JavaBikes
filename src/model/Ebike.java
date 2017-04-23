<<<<<<< HEAD
package model;

public class Ebike extends Bike {
	private int batteryDuration;

	public Ebike(String color, String type, int id, boolean isAvailable, int price) {
		super(color, type, id, isAvailable, price);
		batteryDuration = 3;
	}

	public int getBatteryDuration() {
		return batteryDuration;
	}

	public void setBatteryDuration(int batteryDuration) {
		this.batteryDuration = batteryDuration;
	}

	// method from super class taken and batteryLevel added
	@Override
	public String toString() {
		return super.toString() + " (" + batteryDuration + ")";
	}
}
=======
package model;

public class Ebike extends Bike {
	private int batteryDuration;

	public Ebike(String color, String size, int id, boolean isAvailable) {
		super(color, size, id, isAvailable);
		batteryDuration = 3;
	}

	public int getBatteryLevel() {
		return batteryDuration;
	}

	public void setBatteryLevel(int batteryLevel) {
		this.batteryDuration = batteryLevel;
	}

	// method from super class taken and batteryLevel added
	@Override
	public String toString() {
		return super.toString() + " (" + batteryDuration + ")";
	}
}
>>>>>>> 7285c6e90d3c5157a2673360a880f79388ce7246
