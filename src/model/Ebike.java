package model;

public class Ebike extends Bike {
	private int batteryDuration;

	public Ebike(int id, String color, String type, int price, boolean isAvailable) {
		super(id, color, type, price, isAvailable);
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
