package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Bike;
import model.Ebike;
import model.Customer;

public class FileManipulation {

	public static String FILENAME_CUSTOMERDB = "customer.csv";

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
		customerFromFile.setFirstName(values[0]);
		customerFromFile.setLastName(values[1]);
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

	public static Bike getBike(String line) {
		String[] values = line.split(";");
		Bike BikeFromFile = new Bike(Integer.parseInt(values[0]), values[1], values[2], Integer.parseInt(values[3]),
				Boolean.parseBoolean(values[4]));

		/** alternative line by line assignment
		 * Bike BikeFromFile = new Bike(); 
		 * BikeFromFile.setId(Integer.parseInt(values[0]));
		 * BikeFromFile.setColor(values[1]); BikeFromFile.setType(values[2]);
		 * BikeFromFile.setPrice(Integer.parseInt(values[3]));
		 * BikeFromFile.setAvailable(Boolean.parseBoolean(values[4]));
		 */

		return BikeFromFile;

	}

	public static ArrayList<Bike> getBikeDatabase() { // Stores string
		ArrayList<Bike> arrayBikes = new ArrayList<Bike>();
		Scanner input = readDetails("bike.txt");
		while (input.hasNextLine()) {
			String lineFromFile = input.nextLine();
			Bike bikeFromFile = getBike(lineFromFile);
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

	public static void WriteDetails(String file, String input) {
		try {
			FileWriter fwriter = new FileWriter(file, true);
			PrintWriter output = new java.io.PrintWriter(fwriter);
			// Write formatted output to the file
			output.println(input);
			output.close();
		} catch (IOException ex) {
			// if the file is not accessible, print this message
			System.out.println("Error writing to file '" + file + "'");
		}
	}// WriteDetails
	
	public static void replaceLine(String Line)  {
		ArrayList<Bike> BikeArray = getBikeDatabase();
		Bike bikeFromFile = getBike(Line);
		int position=0;
		try {
			File tmp = File.createTempFile("bike", "tmp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(" !" +bikeFromFile +"!!!!!" + BikeArray);
		for(int i=0; i< BikeArray.size(); i++) {
			position =i; //Position of booked bike
			if(BikeArray.get(i).getId() == (bikeFromFile.getId())) {
				BikeArray.get(i).setAvailable(false);
				System.out.println(" works!"+ bikeFromFile);//check delete
				 }
		String details = BikeArray.get(position).getId() + " ; " + BikeArray.get(position).getColor() + ";" + BikeArray.get(position).getType() 
				+ ";" + BikeArray.get(position).getPrice() + ";" + BikeArray.get(position).isAvailable();
			
		WriteDetails("bike.tmp", details);	
		
		} //end of Forloop
		System.out.println(position +"!true"+BikeArray.toString());
	}
}
//String details = bikeFromFile.getId() + "; " + bikeFromFile.getColor() + ";" + bikeFromFile.getType() 
		//+ ";" + bikeFromFile.getPrice() + ";" + "false";