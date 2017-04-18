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
