package view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import data.FileManipulation;
import model.Bike;
import model.Booking;
import model.Customer;
import model.Ebike;

public class PaymentView {

	public void validateCreditCardDetails(Customer currentCustomer, Bike bikeChoise) {
		Scanner input = new Scanner(System.in);
		boolean validation = false;
		while (!validation) {
			// add validation of correct format >>> HAPPENS IN CARDNUMBER CHECK
			System.out.print("Enter your credit card number: ");
			String cardNumber = input.nextLine();

			String cardExpDate;
			do { // Checks format of the Expiration Date
				System.out.println("\nEnter your card expiration date in format MM/YYYY: ");
				cardExpDate = input.nextLine();
				if (!cardExpDate.matches("\\d{2}/\\d{4}"))
					System.out.println("The format of the date is incorrect. Please try again:");
			} while (!cardExpDate.matches("^(?:1[0-2]|0[1-9])/\\d{4}"));

			System.out.println("\nEnter the CVC number :");
			String cardCvc = input.nextLine();

			if (!checkCardNumber(cardNumber) || !checkCvc(cardCvc) || !checkCardExp(cardExpDate) ) {
				System.out.println("\nYour Credit Card was rejected. Please try again.");
			} else {
				System.out.println("\nYour payment was successful and your booking is confirmed!");
				validation = true;
				FileManipulation.updateAvailability(bikeChoise, false); //Updates Bike Database
				currentCustomer.setCreditCardNumber(cardNumber);
				currentCustomer.setCreditCardCvc(cardCvc);
				currentCustomer.setCreditCardExpiration(cardExpDate);
				// get a receipt for customer
			}
		}
	} //End of Method

	//Luhn's algorithm check
	private Boolean checkCardNumber(String cardNumber) {
		String number = new StringBuilder(cardNumber).reverse().toString();
		int sum = 0;
		Boolean validNumber = false;

		for (int i = 0; i < number.length(); i++) {
			int currentDigit = Integer.parseInt(number.substring(i,i+1));
			if (i % 2 == 0) {
				sum += currentDigit;
			} else {
				currentDigit = currentDigit * 2;
				int d1 = currentDigit % 10;
				int d2 = currentDigit / 10;
				sum = sum + d1 + d2;
			}
		}
		if (sum % 10 == 0 && checkMasterVisa(cardNumber)) 
			validNumber = true;
		return validNumber;
	}

	//Expiration date check
	private boolean checkCardExp(String cardExpiration) {
		DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
		Date date = new Date();
		String CurrentDate = dateFormat.format(date);
		int CurrentMonth = Integer.parseInt(CurrentDate.substring(0, 2));
		int CurrentYear = Integer.parseInt(CurrentDate.substring(3, 7));
		int month = Integer.parseInt(cardExpiration.substring(0, 2));
		int year = Integer.parseInt(cardExpiration.substring(3, 7));

		if ((year == CurrentYear && month<= CurrentMonth) || year < CurrentYear) {
			System.out.println("\t this credit card is expired.");
			return false;}
		else
			return true;
	}

	//checks is the card has a right format (Visa or MasterCard)
	private boolean checkMasterVisa(String cardNumber) {
		boolean MasterVisa = false;
		if (cardNumber.startsWith("4") 
				&& (cardNumber.length() == 13 || cardNumber.length() == 16 || cardNumber.length() == 19)) {
			MasterVisa = true;
		} else if (Integer.parseInt(cardNumber.substring(0, 2)) >= 51
				&& Integer.parseInt(cardNumber.substring(0, 2)) <= 55 && cardNumber.length() == 16) {
			MasterVisa = true;
		}
		return MasterVisa;
	}

	//CVC code check
	private boolean checkCvc(String CardCvc) {
		boolean cvcCorrect = false;
		if (CardCvc.matches("\\d{3}")) {
			cvcCorrect = true;
		} else
			System.out.println("format of the CVV code is wrong");

		return cvcCorrect;
	}

	public void getInvoice(Customer currentCustomer, Bike bikeChoice, Booking currentBooking) {
		DateFormat dateformat  = new SimpleDateFormat ("dd-MM-yyyy hh:mm");
		Date date = new Date();
		String Date = dateformat.format(date);
		String invoice = "";
		
		invoice += "         ---------------------------------------------\n";
		invoice += "                      >  JAVABIKES SHOP <  \n";
		invoice += "         ---------------------------------------------\n\n";
		invoice += "                        ~~ BIKE INFO ~~\n";
		invoice += String.format("                Bike Type:              %9s\n", bikeChoice.getType());
		invoice += String.format("                Color:                     %6s\n", bikeChoice.getColor());
		if (bikeChoice instanceof Ebike) {
			invoice += String.format("                Electric:            %12s\n\n", ((Ebike) bikeChoice).getBatteryDuration() +"h duration");
		} else {
			invoice += String.format("                Electric:                     %3s\n\n", "no");
		}
		invoice += String.format("                Booking ID:                     %6s\n\n", currentBooking.getBookingId());
		invoice += "                        ~~ YOUR INFO ~~\n";
		invoice += String.format("                Name:        %20s\n", currentCustomer.getFirstName());
		invoice += String.format("                Surname:     %20s\n", currentCustomer.getLastName());
		invoice += String.format("                e-mail:%26s\n", currentCustomer.getEmail());
		//invoice += String.format("          Document:              %20s\n\n", currentCustomer.getDocument());
		invoice += "                        ~ PAYMENT INFO ~\n";
		invoice += String.format("                %10s                \n", Date);
		invoice += String.format("                Credit Card:  **** **** **** %4s\n", currentCustomer.getCreditCardNumber().substring(12));
		invoice += String.format("                Total:                 %.2f DKK\n", ((double)currentBooking.getPrice() * currentBooking.getBookedDays() ));		
		invoice += String.format("                Incl.VAT - 25%%:         %.2f DKK\n\n", (currentBooking.getPrice() * currentBooking.getBookedDays() * 0.25));		
	//	invoice += String.format("                Total:                   %.2f DKK\n\n", ((double)currentBike.getPrice()));		
		invoice += "                          THANK YOU!\n\n";
		invoice += "                         ~~~~~~~~~~~~~\n\n";
		invoice += "                         \n";
		invoice += "         ---------------------------------------------\n";
		invoice += "                         ENJOY YOUR RIDE!\n";
		invoice += "         ---------------------------------------------\n";
		System.out.println("\n\n");
		System.out.println(invoice);
	}
}
