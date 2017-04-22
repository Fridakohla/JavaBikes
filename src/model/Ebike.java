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
