package com.service;

import java.util.Scanner;

import com.dao.UsersDAO;
import com.entity.UserAccount;
import com.utils.Constants;

public class UserLogin {
	
	static Scanner sc = new Scanner(System.in);

	private static UserLogin userLoginObject = new UserLogin();

	private UserLogin() {
	}

	public static UserLogin getUserLoginObject() {
		return userLoginObject;
	}
	
	static String email = null;
	
	public boolean login() {
		
		System.out.println(Constants.ENTER_EMAIL);
		email = sc.nextLine();

		while (!UserSignUp.getSignUpObject().isExistingUser(email)) {

			System.out.println(Constants.NO_USER);
			System.out.println("Please Choose Below Options");
			System.out.println("1 Re-Enter Mail Id\n2 Create an account");
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				System.out.println(Constants.ENTER_EMAIL);
				email = sc.nextLine();
				break;
			case "2":
				UserSignUp.getSignUpObject().storeData();
				return false;
			default:
				System.out.println(Constants.WRONG_CHOICE);
				break;
			}
		}

		UserAccount userAccount = UsersDAO.getUsersDaoObject().get(email);
		
		System.out.println(Constants.ENTER_PASSWORD);
		String password = sc.nextLine();
		int failedCount = userAccount.getFailedCount();
		if (failedCount >= 5) {
			System.out.println(Constants.LOCKED);
			return false;
		}

		while (!validatePassword(userAccount , password)) {
			failedCount++;
			userAccount.setFailedCount(failedCount);
			if (failedCount == 5) {
				System.out.println(Constants.OUT_OF_ATTEMPTS);
				System.out.println(Constants.LOCKED);
				userAccount.setAccountStatus("Locked");
				UsersDAO.getUsersDaoObject().update(userAccount);
				return false;
			} else {
				System.out.println(Constants.INCORRECT_PASSWORD);
				password = sc.nextLine();
			}
		}
		
		System.out.println(Constants.LOGIN);
		System.out.println("\nWelcome " + userAccount.getFirstName());
		userAccount.setFailedCount(0);
		UsersDAO.getUsersDaoObject().update(userAccount);
		return true;
	}

	protected static boolean validatePassword(UserAccount userAccount , String password) {
		return userAccount.getPassword().equals(password);
	}

}
