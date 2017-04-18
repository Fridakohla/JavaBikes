package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.Customer;

public class FileManipulation {

	public static Scanner readDetails(String file) {
		Scanner input = new Scanner(System.in);
		try {
			File ReadFile = new java.io.File(file);
			input = new Scanner(ReadFile); // creates a Scanner object to read
											// data from the specified file.
		} catch (FileNotFoundException ex) {
			System.out.println("Error reading the file'" + file + "'");
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

	public static ArrayList<Customer> getCustomerDatabase() { // Stores string
																// customer
																// objects in
																// array list
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		Scanner input = readDetails("customer.txt");
		// checking each line
		while (input.hasNextLine()) {
			customerList.add(getCustomer(input.nextLine()));// passing each line
															// to the method
															// getCustomer which
															// returns a
															// customer
		} // then added to a ArrayList

		return customerList;
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
}
