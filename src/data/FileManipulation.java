package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.Bike;
import model.Customer;
import model.Ebike;

public class FileManipulation {

	public static String FILENAME_CUSTOMERDB = "customer.csv";
	public static String FILENAME_BIKEDB = "bike.txt";

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

		} // then added to a ArrayList

		return customerList;
	}

	// write customer database --> clearing before writing
	public static void writeCustomerList(ArrayList<Customer> customerList) {
		clearFileContent(FILENAME_CUSTOMERDB);
		for (int i = 0; i < customerList.size(); i++) {
			customerList.get(i).writetoFile();
		}
	}

	// write customer database --> clearing before writing
	public static void writeBikeList(ArrayList<Bike> bikeList) {
		clearFileContent(FILENAME_BIKEDB);
		for (int i = 0; i < bikeList.size(); i++) {
			String bikeLine = bikeList.get(i).toFileString();
			writeDetails(FILENAME_BIKEDB, bikeLine);
		}
	}

	public static Bike getBikeFromFileString(String line) {
		String[] values = line.split(";");
		Bike BikeFromFile = new Bike(Integer.parseInt(values[0]), values[1], values[2], Integer.parseInt(values[3]),
				Boolean.parseBoolean(values[4]));

		/**
		 * alternative line by line assignment Bike BikeFromFile = new Bike();
		 * BikeFromFile.setId(Integer.parseInt(values[0]));
		 * BikeFromFile.setColor(values[1]); BikeFromFile.setType(values[2]);
		 * BikeFromFile.setPrice(Integer.parseInt(values[3]));
		 * BikeFromFile.setAvailable(Boolean.parseBoolean(values[4]));
		 */

		return BikeFromFile;

	}

	public static ArrayList<Bike> getBikeDatabase() { // Stores string
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

	public static ArrayList<Ebike> getEbikeDatabase() { // Stores string
		ArrayList<Ebike> arrayEBikes = new ArrayList<Ebike>();
		Scanner input = readDetails("Ebike.txt");
		while (input.hasNextLine()) {
			String lineFromFile = input.nextLine();
			Ebike bikeFromFile = getEbike(lineFromFile);
			arrayEBikes.add(bikeFromFile);
		}
		return arrayEBikes;
	}

	public static void writeDetails(String file, String input) {
		try {
			FileWriter fwriter = new FileWriter(file, true); // false does
																// overwrite
			PrintWriter output = new java.io.PrintWriter(fwriter);
			// Write formatted output to the file
			output.println(input); // output.write(input);
			output.close();
		} catch (IOException ex) {
			// if the file is not accessible, print this message
			System.out.println("Error writing to file '" + file + "'");
		}
	}// End of Method WriteDetails

	public static void updateAvailability(Bike bikeObject) { // >>>confirm
																// booking
		String fileName = null;
		System.out.println(bikeObject.toString());
		if (bikeObject instanceof Ebike) {
			ArrayList<Ebike> BikeArray = getEbikeDatabase();
			fileName = "ebike.txt";
			clearFileContent(fileName);
			for (int i = 0; i < BikeArray.size(); i++) {
				if (BikeArray.get(i).getId() == (bikeObject.getId())) {
					BikeArray.get(i).setAvailable(false);
				}
				String details = BikeArray.get(i).toFileString();
				writeDetails(fileName, details);
			} // end of Forloop
		} else {
			ArrayList<Bike> BikeArray = getBikeDatabase();
			fileName = "bike.txt";
			clearFileContent(fileName);
			for (int i = 0; i < BikeArray.size(); i++) {
				if (BikeArray.get(i).getId() == (bikeObject.getId())) {
					BikeArray.get(i).setAvailable(false);
				}
				String details = BikeArray.get(i).toFileString();
				writeDetails(fileName, details);
			} // end of Forloop
		}

	} // End of Method

	public static void clearFileContent(String fileName) { // clears all content
															// from file
		try {
			PrintWriter pw = new PrintWriter(fileName);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

// String details = bikeFromFile.getId() + "; " + bikeFromFile.getColor() + ";"
// + bikeFromFile.getType()
// + ";" + bikeFromFile.getPrice() + ";" + "false";
// System.out.println(" !" +bikeFromFile +"!!!!!" + BikeArray);
// String details = BikeArray.get(i).getId() + ";" + BikeArray.get(i).getColor()
// + ";" + BikeArray.get(i).getType()
// + ";" + BikeArray.get(i).getPrice() + ";" + BikeArray.get(i).isAvailable();
/**
 * HOW TO CALL THE METHOD //String Line = "2;yellow;women;50;true";//<<<TEST
 * //FileManipulation.replaceLine (getBike("2;yellow;women;50;true"));//<<<TEST
 */

// Bike testBike = FileManipulation.getBike("2;yellow;women;50;true");
// FileManipulation.updateAvailability(testBike);

// System.out.println(i +"!true"+BikeArray.get(i).BiketoString());
// end of Forloop
// End of Method

// String details = bikeFromFile.getId() + "; " + bikeFromFile.getColor() + ";"
// + bikeFromFile.getType()
// + ";" + bikeFromFile.getPrice() + ";" + "false";
// System.out.println(" !" +bikeFromFile +"!!!!!" + BikeArray);
// String details = BikeArray.get(i).getId() + ";" + BikeArray.get(i).getColor()
// + ";" + BikeArray.get(i).getType()
// + ";" + BikeArray.get(i).getPrice() + ";" + BikeArray.get(i).isAvailable();
/**
 * HOW TO CALL THE METHOD //String Line = "2;yellow;women;50;true";//<<<TEST
 * //FileManipulation.replaceLine (Line);//<<<TEST to call eBike: Bike testBike
 * = FileManipulation.getEbike("14;orange;child;55;true;7");
 * FileManipulation.updateAvailability(testBike);
 */
