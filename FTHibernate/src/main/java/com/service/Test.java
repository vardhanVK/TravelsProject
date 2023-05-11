package com.service;

import java.util.Scanner;

import com.utils.Constants;

public class Test {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		boolean isLoggedin = false;
		LogoDisplay.logoDisplay();
		boolean flag = true;
		while (flag) {
			printMenu();
			switch (sc.nextLine()) {

			case "1":
				if (isLoggedin)
					System.out.println("Log Out Before Trying to Sign Up");
				else {
					UserSignUp.getSignUpObject().storeData();
					System.out.println("Please Login Now!!");
				}
				break;

			case "2":
				
				if (isLoggedin) 
					System.out.println("You Are Already Logged In!!");
				else if (UserLogin.getUserLoginObject().login())
					isLoggedin = true;
				else
					System.out.println("Login Un Successful !!");
				break;

			case "3":
				if (isLoggedin) 
					new PlanJourney().bookTicket();
				else
					System.out.println("Please Login to Book Tickets !!");
				break;

			case "4":
				if (isLoggedin) {
					isLoggedin = false;
					System.out.println("\nSuccessfully Logged Out !!");
				} else 
					System.out.println("\nNo User Logged In");
				break;

			case "5":
				if (isLoggedin)
					System.out.println("Log Out Before Trying to Unlock Your Account");
				else
					Adminstrator.getAdminObj().unLockAccount();
				break;

			case "6":
				if (isLoggedin)
					Adminstrator.getAdminObj().myBookings(UserLogin.email);
				else
					System.out.println("Please Login To View Your Bookings !!");
				break;

			case "7":
				flag = false;
				break;

			default:
				System.out.println(Constants.WRONG_CHOICE);
				break;
			}
		}
	}

	private static void printMenu() {
		System.out.println("\nPlease Choose From Below Options : \n");
		System.out.println(
				"1 Create Account\n2 Login\n3 Book Tickets\n4 Log Out\n5 Un Lock Account\n6 My Bookings\n7 Exit Application");
	}
}
