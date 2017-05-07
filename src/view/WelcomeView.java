package view;

import java.util.Scanner;

public class WelcomeView {
	public static final int MENUCHOICE_LOGIN = 1;
	public static final int MENUCHOICE_REGISTER = 2;
	public static final int MENUCHOICE_ADMIN = 3;

	// displays welcome message and lets user chose a login or register option
	public int loginMenu() {
		int choice = 0;
		printWelcomeToJavaBikes();
		System.out.println("Hey there! Are you already a customer or do you want to register?");
		while (choice != 1 && choice != 2 && choice != 3) {
			Scanner input = new Scanner(System.in);
			System.out.println("|1| Login as an existing customer.");
			System.out.println("|2| Register as a new customer.");
			System.out.println("|3| Login as admin.\n");
			choice = input.nextInt();
			if (choice != 1 && choice != 2 && choice != 3) {
				System.out.println("Sorry, invalid input. Please make sure to type a valid option.");
			}
		}
		return choice;
	}

	private void printWelcomeToJavaBikes() {
		System.out.println(
				" _       _         _                                       _              _____                       ___       _             ");
		System.out.println(
				"( )  _  ( )       (_ )                                    ( )_           (___  )                     (  _`\\  _ ( )           ");
		System.out.println(
				"| | ( ) | |   __   | |    ___    _     ___ ___     __     | ,_)   _          | |   _ _  _   _    _ _ | (_) )(_)| |/')    __    ___ ");
		System.out.println(
				"| | | | | | /'__`\\ | |  /'___) /'_`\\ /' _ ` _ `\\ /'__`\\   | |   /'_`\\     _  | | /'_` )( ) ( ) /'_` )|  _ <'| || , <   /'__`\\/',__)");
		System.out.println(
				"| (_/ \\_) |(  ___/ | | ( (___ ( (_) )| ( ) ( ) |(  ___/   | |_ ( (_) )   ( )_| |( (_| || \\_/ |( (_| || (_) )| || |\\`\\ (  ___/\\__, \\");
		System.out.println(
				"`\\___x___/'`\\____)(___)`\\____)`\\___/'(_) (_) (_)`\\____)   `\\__)`\\___/'   `\\___/'`\\__,_)`\\___/'`\\__,_)(____/'(_)(_) (_)`\\____)(____/");

		System.out.println(
				"_____________________________________________________________________________________________________________________________________\n");

	}
}