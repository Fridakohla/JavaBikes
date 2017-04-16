package model;

public class Ebike extends Bike {
	private int batteryLevel;

	public Ebike(String color, String size, int id, boolean isAvailable) {
		super(color, size, id, isAvailable);
		batteryLevel = 100;
	}

	public int getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	// method from super class taken and batteryLevel added
	@Override
	public String toString() {
		return super.toString() + " (" + batteryLevel + ")";
	}
}
