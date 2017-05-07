package controller;

import model.Bike;
import model.Customer;
import model.Ebike;
import view.AdminView;
import view.CustomerView;

public class AdminController {

	public AdminView adminView = new AdminView();

	public void runAdmin() {
		// admin login check -- if false, program ends
		boolean adminContinue = adminView.adminLogin();
		while (adminContinue) {
			boolean correctInput = false;
			while (!correctInput) {
				int choice = adminView.adminMainMenu();
				correctInput = true;
				switch (choice) {
				case AdminView.MENUCHOICE_DELETECUSTOMER:
					adminDeleteCustomer();
					break;
				case AdminView.MENUCHOICE_MANAGEBIKES:
					// add and remove -- bikes or ebikes
					adminManageBikes();
					break;
				case AdminView.MENUCHOICE_VIEWBOOKINGS:
					adminView.displayBookingList(JavaBikesController.bookingDb);
					break;
				case CustomerView.MENUCHOICE_EXIT:
					System.out.println("You have exited the program.");
					adminContinue = false;
					break;
				default:
					correctInput = false;
					System.out.println("Invalid input. Please type a valid option.");
				}
			}
		}
	}

	private void adminManageBikes() {
		boolean correctInput = false;
		CustomerView.displayRegularBikes(JavaBikesController.bikeDb);
		CustomerView.displayElectricBikes(JavaBikesController.bikeDb);
		while (!correctInput) {
			correctInput = true;
			int choice = adminView.manageBikesMenu();
			switch (choice) {
			case AdminView.MENUCHOICE_REMOVEBIKE:
				adminDeleteRegularBike();
				break;
			case AdminView.MENUCHOICE_REMOVEEBIKE:
				adminDeleteElectricBike();
				break;
			case AdminView.MENUCHOICE_ADDBIKE:
				adminAddRegularBike();
				break;
			case AdminView.MENUCHOICE_ADDEBIKE:
				adminAddElectricBike();
				break;
			case AdminView.MENUCHOICE_MAINMENU:
				break;
			default:
				correctInput = false;
			}
		}
	}

	private void adminAddRegularBike() {
		Bike addedBike = new Bike();
		adminView.addRegularBike(addedBike);
		JavaBikesController.bikeDb.addBike(addedBike);
		System.out.println("You have added --> " + addedBike + " <-- to the database.");
		CustomerView.displayRegularBikes(JavaBikesController.bikeDb);
	}

	private void adminAddElectricBike() {
		Ebike addedEbike = new Ebike();
		adminView.addEbike(addedEbike);
		JavaBikesController.bikeDb.addBike(addedEbike);
		System.out.println("You have added --> " + addedEbike + " <-- to the database.");
		CustomerView.displayElectricBikes(JavaBikesController.bikeDb);
	}

	private void adminDeleteRegularBike() {
		Bike deletedBike;
		deletedBike = adminView.selectRegularBikeFromList(JavaBikesController.bikeDb);
		if (deletedBike != null) {
			JavaBikesController.bikeDb.removeRegularBike(deletedBike);
			System.out.println("You have deleted --> " + deletedBike + " <-- from the database.");
			CustomerView.displayRegularBikes(JavaBikesController.bikeDb);
		}

	}

	private void adminDeleteElectricBike() {
		Bike deletedBike;
		deletedBike = adminView.selectElectricBikeFromList(JavaBikesController.bikeDb);
		if (deletedBike != null) {
			JavaBikesController.bikeDb.removeElectricBike(deletedBike);
			System.out.println("You have deleted --> " + deletedBike + " <-- from the database.");
			CustomerView.displayElectricBikes(JavaBikesController.bikeDb);
		}

	}

	private void adminDeleteCustomer() {
		Customer deletedCustomer;
		deletedCustomer = adminView.selectCustomerFromList(JavaBikesController.customerDb);
		if (deletedCustomer != null) {
			JavaBikesController.customerDb.removeCustomer(deletedCustomer);
			System.out.println("You have deleted --> " + deletedCustomer + " <-- from the customer database.\n");
			adminView.displayCustomerList(JavaBikesController.customerDb);
		}
	}
}
