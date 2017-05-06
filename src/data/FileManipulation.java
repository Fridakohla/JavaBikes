package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.Bike;
import model.BikeDatabase;
import model.Booking;
import model.Customer;
import model.CustomerDatabase;
import model.Ebike;

public class FileManipulation {

	public static String FILENAME_CUSTOMERDB = "customer.csv";
	public static String FILENAME_BIKEDB = "bike.csv";
	public static String FILENAME_EBIKEDB = "ebike.csv";
	public static String FILENAME_BOOKINGDB = "bookingDatabase.csv";

	public static Scanner readDetails(String filename) {
		Scanner input = new Scanner(System.in);
		try {
			File readFile = new File(filename);
			input = new Scanner(readFile); // creates a Scanner object to read
											// data from the specified file.
		} catch (FileNotFoundException ex) {
			System.out.println("Error reading the file'" + filename + "'");
		}
		return input;
	}// readDetails

	public static Customer getCustomer(String line) {
		Customer customerFromFile = new Customer();
		// Look for every ";" and turns the values into strings
		String[] values = line.split(";");

		// Change the String type into the correct format
		customerFromFile.setFirstName(values[1]);
		customerFromFile.setLastName(values[0]);
		customerFromFile.setUsername(values[2]);
		customerFromFile.setPassword(values[3]);
		customerFromFile.setAddress(values[4]);
		customerFromFile.setCpr(values[5]);
		customerFromFile.setEmail(values[6]);

		return customerFromFile;

	}

	// Stores string customer objects in array list
	public static ArrayList<Customer> getCustomerDatabase() {
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		Scanner input = readDetails(FILENAME_CUSTOMERDB);
		// checking each line
		while (input.hasNextLine()) {
			String lineFromFile = input.nextLine();
			Customer customerFromFile = getCustomer(lineFromFile);
			customerList.add(customerFromFile);
		}
		return customerList;
	}

	// write customer a single customer to the database file (adding)
	public static void writeCustomerList(ArrayList<Customer> customerList) {
		clearFileContent(FILENAME_CUSTOMERDB);
		for (int i = 0; i < customerList.size(); i++) {
			writeCustomer(customerList.get(i));
		}
	}

	public static void writeCustomer(Customer c) {
		String details = c.toFileString();
		writeDetails(FILENAME_CUSTOMERDB, details);
	}

	// write booking database --> clearing before writing
	public static void writeBookingList(ArrayList<Booking> bookingList) {
		clearFileContent(FILENAME_BOOKINGDB);
		for (int i = 0; i < bookingList.size(); i++) {
			writeBooking(bookingList.get(i));
		}
	}

	public static void writeBooking(Booking b) {
		String details = b.toFileString();
		writeDetails(FILENAME_BOOKINGDB, details);
	}

	//
	public static void writeRegularBikeList(ArrayList<Bike> bikeList) {
		clearFileContent(FILENAME_BIKEDB);
		for (int i = 0; i < bikeList.size(); i++) {
			String bikeLine = bikeList.get(i).toFileString();
			writeDetails(FILENAME_BIKEDB, bikeLine);
		}
	}

	public static void writeElectricBikeList(ArrayList<Ebike> ebikeList) {
		clearFileContent(FILENAME_EBIKEDB);
		for (int i = 0; i < ebikeList.size(); i++) {
			String bikeLine = ebikeList.get(i).toFileString();
			writeDetails(FILENAME_EBIKEDB, bikeLine);
		}
	}

	public static Bike getBikeFromFileString(String line) {
		String[] values = line.split(";");
		Bike BikeFromFile = new Bike(Integer.parseInt(values[0]), values[1], values[2], Integer.parseInt(values[3]),
				Boolean.parseBoolean(values[4]));
		return BikeFromFile;

	}

	public static ArrayList<Bike> getBikeDatabase() {
		ArrayList<Bike> arrayBikes = new ArrayList<Bike>();
		Scanner input = readDetails(FILENAME_BIKEDB);
		while (input.hasNextLine()) {
			String lineFromFile = input.nextLine();
			Bike bikeFromFile = getBikeFromFileString(lineFromFile);
			arrayBikes.add(bikeFromFile);
		}
		return arrayBikes;
	}

	public static Ebike getEbike(String line) {
		String[] values = line.split(";");
		Ebike BikeFromFile = new Ebike(Integer.parseInt(values[0]), values[1], values[2], Integer.parseInt(values[3]),
				Boolean.parseBoolean(values[4]), Integer.parseInt(values[5]));

		return BikeFromFile;
	}

	public static ArrayList<Ebike> getEbikeDatabase() {
		ArrayList<Ebike> arrayEBikes = new ArrayList<Ebike>();
		Scanner input = readDetails(FILENAME_EBIKEDB);
		while (input.hasNextLine()) {
			String lineFromFile = input.nextLine();
			Ebike bikeFromFile = getEbike(lineFromFile);
			arrayEBikes.add(bikeFromFile);
		}
		return arrayEBikes;
	}

	public static void writeDetails(String file, String input) {
		try {
			FileWriter fwriter = new FileWriter(file, true); // true adds new
																// line, false
																// does
																// overwrite
			PrintWriter output = new java.io.PrintWriter(fwriter);
			// Writes formatted output to the file
			output.println(input);
			output.close();
		} catch (IOException ex) {
			// if the file is not accessible, print this message
			System.out.println("Error writing to file '" + file + "'");
		}
	}// End of Method WriteDetails

	// method to update availability of bikes in Database (true for available,
	// false for taken)
	public static void updateAvailability(Bike bikeObject, boolean available) {
		// System.out.println(bikeObject.toString());
		if (bikeObject instanceof Ebike) {
			ArrayList<Ebike> BikeArray = getEbikeDatabase();
			clearFileContent(FILENAME_EBIKEDB);
			for (int i = 0; i < BikeArray.size(); i++) {
				if (BikeArray.get(i).getId() == (bikeObject.getId())) {
					BikeArray.get(i).setAvailable(available);
				}
				String details = BikeArray.get(i).toFileString();
				writeDetails(FILENAME_EBIKEDB, details);
			} // end of For loop
		} else {
			ArrayList<Bike> BikeArray = getBikeDatabase();
			clearFileContent(FILENAME_BIKEDB);
			for (int i = 0; i < BikeArray.size(); i++) {
				if (BikeArray.get(i).getId() == (bikeObject.getId())) {
					BikeArray.get(i).setAvailable(available);
				}
				String details = BikeArray.get(i).toFileString();
				writeDetails(FILENAME_BIKEDB, details);
			} // end of For loop
		}
	} // End of Method

	// Clears all content from file
	public static void clearFileContent(String fileName) {
		try {
			PrintWriter pw = new PrintWriter(fileName);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Booking getBookingFromFile(String line) {
		String[] values = line.split(";");
		Booking bookingFromFile = new Booking();
		bookingFromFile.setBookingId(values[0]);
		bookingFromFile.setPrice(Integer.parseInt(values[3]));
		bookingFromFile.setBookedDays(Integer.parseInt(values[4]));
		bookingFromFile.setStartTime(values[5]);
		// get null instead "null" from file to have one way of expressing not
		// returned
		if (values[6].equals("null")) {
			bookingFromFile.setReturnDate(null);
		} else {
			bookingFromFile.setReturnDate(values[6]);
		}

		// get bike object
		Bike myBike = BikeDatabase.getBikeByID(Integer.parseInt(values[2]));
		// if the bike is an ebike then he could not find it in the bike list.
		// Then look in the ebike list.
		if (myBike == null) {
			myBike = BikeDatabase.getEbikeByID(Integer.parseInt(values[2]));
		}
		bookingFromFile.setBike(myBike);
		// get customer object
		Customer c = CustomerDatabase.getCustomerByUsername(values[1]);
		bookingFromFile.setCustomer(c);

		return bookingFromFile;
	}

	public static ArrayList<Booking> getBookingDatabase() {
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		Scanner input = readDetails(FILENAME_BOOKINGDB);
		// checking each line
		while (input.hasNextLine()) {
			String lineFromFile = input.nextLine();
			Booking bookingFromFile = getBookingFromFile(lineFromFile);
			bookingList.add(bookingFromFile);
		}
		return bookingList;
	}

	// // method to set a return date and send a bike back to database
	// public static void returnBike(String BookingId, String dateOfReturn) {
	// ArrayList<Booking> bookingList = getBookingDatabase();
	// clearFileContent(FILENAME_BOOKINGDB);
	// for (int i = 0; i < bookingList.size(); i++) {
	// if (bookingList.get(i).getBookingId().equals(BookingId)) {
	// bookingList.get(i).setReturnDate(dateOfReturn);
	// }
	// String details = bookingList.get(i).toString();
	// writeDetails(FILENAME_BOOKINGDB, details);
	// } // end of Forloop
	// }
}
