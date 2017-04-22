package model;

public class Bike {
	private String color, type;
	private int id;
	private boolean isAvailable;
	private int price;

	public Bike(String color, String type, int id, boolean isAvailable, int price) {
		this.color = color;
		this.type = type;
		this.id = id;
		this.isAvailable = isAvailable;
		this.price = price;
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
		return type;
	}

	public void setSize(String size) {
		this.type = size;
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
