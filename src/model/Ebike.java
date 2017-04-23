package model;

public class Ebike extends Bike {
	private int batteryDuration;

	public Ebike(int id, String color, String type, int price, boolean isAvailable, int batteryDuration) {
		super(id, color, type, price, isAvailable);
		this.batteryDuration = batteryDuration;
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
