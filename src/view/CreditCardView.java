package view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.Customer;

public class CreditCardView {
	private String CardNumber;
	private String CardExpDate;
	private String CardCvv;
	Scanner input = new Scanner(System.in);
	
	
	public void validateCreditCardDetails() { 
		
		System.out.print("Enter your credit card number: ");
		CardNumber = input.nextLine();
		
		do { //Checks format of the Expiration Date
		System.out.print("Enter your card expiration date in format MM/YYYY: ");
		CardExpDate = input.nextLine();
		if(!CardExpDate.matches("\\d{2}/\\d{4}"))
			System.out.println("The format of the date is incorrect. Please try again.");
		}while (!CardExpDate.matches("^(?:1[0-2]|0[1-9])/\\d{4}"));
		
		System.out.println(" Please enter the CVV number :");
		CardCvv = input.nextLine();
		
		boolean validation = false;
		while(!validation) {
		if(!checkCardNumber(CardNumber) || !CheckCVV(CardCvv)) {
			System.out.println("Your Credit Card was rejected. Please try again.");
			 break;}
		
		}
	}
	
	private Boolean checkCardNumber(String CardNumber) {
		String number = new StringBuilder(CardNumber).reverse().toString();
		int sum=0;
		Boolean validNumber= false;

		for (int i = 0; i < number.length(); i++) {
			int currentDigit = Integer.parseInt(number.substring(i));
			if (i % 2 == 0) {
				sum += currentDigit;
			} else {
				currentDigit = currentDigit * 2;
					int d1 = currentDigit % 10;
					int d2 = currentDigit / 10;
					sum = sum +d1 +d2;
				}
			}
		if(sum % 10 == 0 && checkMasterVisa(CardNumber))
			validNumber = true;
		return validNumber;
	}
		
	private boolean checkCardExp(String CardExpiration) {
		DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
		Date date = new Date();
		String CurrentDate = dateFormat.format(date);
		
		return false;
	}
	
	
		private boolean checkMasterVisa(String CardNumber) {
	        boolean MasterVisa=false;
		    if (CardNumber.startsWith("4") &&
			     (CardNumber.length() == 13 || CardNumber.length() == 16 ||CardNumber.length() == 19)) {
				MasterVisa = true;
			}
		    else if (Integer.parseInt(CardNumber.substring(0, 2)) >= 51 &&
		    		Integer.parseInt(CardNumber.substring(0, 2)) <= 55 && CardNumber.length() == 16) {
				MasterVisa = true;
			}
		return MasterVisa;
	}
	
	private boolean CheckCVV(String CardCvv) {
		boolean cvvCorrect = false;
			if (CardCvv.matches("\\d{3}")) {
				cvvCorrect = true;
			} else
				System.out.println("format of the CVV code is wrong");
		
		   return cvvCorrect;
	}

}
