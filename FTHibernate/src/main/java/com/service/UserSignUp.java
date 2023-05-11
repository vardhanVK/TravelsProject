package com.service;

import java.util.Scanner;

import com.dao.UsersDAO;
import com.entity.UserAccount;
import com.utils.Constants;

public class UserSignUp {

	private UserSignUp() {
	}

	private static UserSignUp signUpObj = new UserSignUp();

	public static UserSignUp getSignUpObject() {
		return signUpObj;
	}

	static Scanner sc = new Scanner(System.in);

	public void storeData() {

		String email = storeEmail();

		String password = storePassword();

		System.out.println(Constants.FIRST_NAME);
		String firstName = storeName();

		System.out.println(Constants.LAST_NAME);
		String lastName = storeName();

		String mobileNumber = storeMobileNumber();

		String gender = storeGender();

		UserAccount user = new UserAccount(firstName, lastName, mobileNumber, gender, email, password);
		UsersDAO.getUsersDaoObject().save(user);
		System.out.println(Constants.SIGNUP);
	}

	protected String storeGender() {

		System.out.println(Constants.ENTER_GENDER);
		String gender = sc.nextLine();
		gender = gender.toUpperCase();
		while (!isValidGender(gender)) {
			System.out.println(Constants.INVALID_GENDER);
			gender = sc.nextLine();
			gender = gender.toUpperCase();
		}
		return gender;
	}

	private String storeMobileNumber() {

		System.out.println(Constants.ENTER_MOBILE_NUMBER);
		String mobileNumber = sc.nextLine();

		while (!signUpObj.isValidMobileNumber(mobileNumber)) {
			System.out.println(Constants.INVALID_MOBILE_NUMBER);
			mobileNumber = sc.nextLine();
		}
		return mobileNumber;
	}

	protected String storeName() {

		String name = sc.nextLine();
		while (!signUpObj.isValidName(name)) {
			System.out.println(Constants.INVALID_NAME);
			name = sc.nextLine();
		}
		return name;
	}

	private String storePassword() {

		System.out.println(Constants.ENTER_PASSWORD);
		String password = sc.nextLine();

		while (!signUpObj.isValidPassword(password)) {
			System.out.println(Constants.INVALID_PASSWORD);
			System.out.println(Constants.ENTER_PASSWORD);
			password = sc.nextLine();
		}
		return password;
	}

	private String storeEmail() {

		System.out.println(Constants.ENTER_EMAIL);
		String email = sc.nextLine();

		while (isExistingUser(email)) {
			System.out.println(Constants.EMAIL_EXISTS);
			System.out.println(Constants.ENTER_EMAIL);
			email = sc.nextLine();
		}

		while (!isValidEmail(email)) {
			System.out.println(Constants.INVALID_EMAIL + Constants.ENTER_EMAIL);
			email = sc.nextLine();
		}

		return email;
	}

	private boolean isValidPassword(String password) {
		boolean hasUpperCase = false;
		boolean hasLowerCase = false;
		boolean hasDigit = false;
		for (char c : password.toCharArray()) {
			if (Character.isUpperCase(c))
				hasUpperCase = true;
			else if (Character.isLowerCase(c))
				hasLowerCase = true;
			else if (Character.isDigit(c))
				hasDigit = true;
		}
		return hasLowerCase && hasUpperCase && hasDigit && password.length() >= 6;
	}

	private boolean isValidName(String name) {
		return name.trim().matches("[a-zA-Z]+");
	}

	private boolean isValidMobileNumber(String mobileNumber) {
		return (mobileNumber.matches("[0-9]*$")) && (mobileNumber.length() == 10);
	}

	protected boolean isExistingUser(String email) {
		return UsersDAO.getUsersDaoObject().get(email) != null;
	}

	private boolean isValidEmail(String email) {
		return email.contains("@") && email.contains(".") && !email.contains("@.");
	}

	private static boolean isValidGender(String gender) {
		return gender.equals("M") || gender.equals("F") || gender.equals("O");
	}

}
