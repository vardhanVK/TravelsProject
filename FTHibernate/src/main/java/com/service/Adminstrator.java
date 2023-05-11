package com.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dao.BookingDAO;
import com.dao.UsersDAO;
import com.entity.Booking;
import com.entity.UserAccount;
import com.utils.Constants;

public class Adminstrator {

	private Adminstrator() {
	}

	static Adminstrator busAdminObj = new Adminstrator();

	public static Adminstrator getAdminObj() {
		return busAdminObj;
	}

	static Scanner sc = new Scanner(System.in);

	public void unLockAccount() {

		System.out.println(Constants.ENTER_EMAIL);
		String email = sc.nextLine();

		while (!UserSignUp.getSignUpObject().isExistingUser(email)) {

			System.out.println(Constants.NO_USER);
			System.out.println(Constants.ENTER_EMAIL);
			email = sc.nextLine();
		}

		UserAccount user = UsersDAO.getUsersDaoObject().get(email);

		System.out.println(Constants.ENTER_PASSWORD);
		while (!UserLogin.validatePassword(user, sc.nextLine())) {
			System.out.println(Constants.INCORRECT_PASSWORD);
		}

		if (user.getAccountStatus().equals("Locked")) {
			user.setAccountStatus("Active");
			user.setFailedCount(0);
			UsersDAO.getUsersDaoObject().update(user);
			System.out.println("\nAccount Un Locked");
		} else
			System.out.println("Your Account is Already Active !!");
	}

	public boolean myBookings(String email) {

		List<Booking> bookings = BookingDAO.getBookingDaoObject().getAll(email);

		if (bookings.isEmpty()) {
			System.out.println("No Bookings Found!!!");
			return true;
		}

		List<Booking> previousBookings = getPreviousBookings(bookings);
		bookings.removeAll(previousBookings);
		List<Booking> upcomingBookings = new ArrayList<>(bookings);

		displayBookings(previousBookings, Constants.PREVIOUS_BOOKINGS);
		displayBookings(upcomingBookings, Constants.UPCOMING_BOOKINGS);

		while (true) {
			System.out.println("\nChoose From Below Options :\n1Cancel Booking\n2Re-Schedule Booking\n3Go Back");
			switch (sc.nextLine()) {
			case "1":
				if (upcomingBookings.isEmpty()) {
					System.out.println(Constants.NO_BOOKINGS);
					break;
				} else {
					cancelBooking(upcomingBookings);
					return true;
				}

			case "2":
				if (upcomingBookings.isEmpty()) {
					System.out.println(Constants.NO_BOOKINGS);
					break;
				} else {
					rescheduleBooking(upcomingBookings);
					return true;
				}

			case "3":
				return false;

			default:
				System.out.println(Constants.WRONG_CHOICE);
				break;
			}
		}
	}

	private void displayBookings(List<Booking> bookings, String bookingType) {
		if (!bookings.isEmpty()) {
			System.out.println(bookingType);
			for (Booking booking : bookings) {
				System.out.printf("Bus Number : %-18dBooking Id : %d%n", booking.getBusNumber(),
						booking.getBookingId());
				System.out.printf("%-20S%-20S%-20S%n%n", booking.getBoardingStation(), booking.getDestinationStation(),
						booking.getDate());
			}
		}
	}

	private static boolean validateBookingId(int bookingId, List<Booking> upcomingBookings) {
		for (Booking booking : upcomingBookings) {
			if (booking.getBookingId() == bookingId)
				return true;
		}
		return false;
	}

	private List<Booking> getPreviousBookings(List<Booking> bookings) {
		
		List<Booking> previousBookings = new ArrayList<>();
		for (Booking booking : bookings) {
			if (booking.getDate().isBefore(LocalDate.now()))
				previousBookings.add(booking);
		}
		return previousBookings;
	}

	private static void cancelBooking(List<Booking> upcomingBookings) {

		int bookingId = PlanJourney.chooseValue(Constants.ENTER_BOOKING_ID);
		while (!validateBookingId(bookingId, upcomingBookings)) {
			System.out.println(Constants.INVALID_BOOKING_ID);
			bookingId = PlanJourney.chooseValue(Constants.ENTER_BOOKING_ID);
		}

		BookingDAO.getBookingDaoObject().delete(BookingDAO.getBookingDaoObject().get(bookingId));
		System.out.println(Constants.BOOKING_CANCELLED);
	}

	private static void rescheduleBooking(List<Booking> upcomingBookings) {

		int bookingId = PlanJourney.chooseValue(Constants.ENTER_BOOKING_ID);
		while (!validateBookingId(bookingId, upcomingBookings)) {
			System.out.println(Constants.INVALID_BOOKING_ID);
			bookingId = PlanJourney.chooseValue(Constants.ENTER_BOOKING_ID);
		}

		String date = PlanJourney.chooseDate();
		Booking booking = BookingDAO.getBookingDaoObject().get(bookingId);
		booking.setDate(LocalDate.parse(date));
		BookingDAO.getBookingDaoObject().update(booking);
		System.out.println(Constants.BOOKING_RESCHEDULED);
	}
}
