package model;

public class Bike {
	private String color, size;
	private int id;
	private boolean isAvailable;

	public Bike(String color, String size, int id, boolean isAvailable) {
		this.color = color;
		this.size = size;
		this.id = id;
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Bike ID: " + id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}
