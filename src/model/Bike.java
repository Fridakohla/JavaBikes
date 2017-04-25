package model;
// TEST 

public class Bike {
	private String color, type;
	private int id;
	private boolean isAvailable;
	private int price;

	public Bike(int id, String color, String type, int price, boolean isAvailable) {
		this.color = color;
		this.type = type;
		this.id = id;
		this.isAvailable = isAvailable;
		this.price = price;
	}

	@Override
	public String toString() {
		return "a " + color + " " + type + "'s bike for " + price + " DKK"+ isAvailable;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}