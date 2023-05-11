package com.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.dao.AvailableBusesDAO;
import com.dao.BookingDAO;
import com.dao.BusDAO;
import com.dao.StopsDAO;
import com.entity.Booking;
import com.entity.Bus;
import com.entity.Passenger;
import com.utils.Constants;

public class PlanJourney {

	static Scanner sc = new Scanner(System.in);
	Set<String> stations = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
	
	public boolean bookTicket() {

		stations = getAllStations();
		displayAllStations(stations);

		System.out.println(Constants.BOARDING_STATION);
		String boardingStation = chooseStation();

		stations.remove(boardingStation);
		displayAllStations(stations);
		
		System.out.println(Constants.DESTINATION_STATION);
		String destinationStation = chooseStation();

		String date = chooseDate();

		List<Object[]> availableBuses = getAvailableBuses(boardingStation, destinationStation);

		if (availableBuses.isEmpty()) {
			System.out.println(Constants.NO_BUSES);
			bookTicket();
		}

		printBuses(availableBuses);

		int busNum = chooseValue(Constants.BUS_NUMBER);

		while (!isValidBusNumber(availableBuses, busNum)) {
			System.out.println(Constants.INVALID_BUS_NUMBER);
			busNum = chooseValue(Constants.BUS_NUMBER);
		}

		Bus busDetails = BusDAO.getBusDaoObject().get(busNum);

		int passengersCount = chooseValue(Constants.NUMBER_OF_PASSENGERS);

		while (!isValidPassengerCount(busDetails, passengersCount)) {
			System.out.println(Constants.INVALID_PASSENGERS);
			passengersCount = chooseValue(Constants.NUMBER_OF_PASSENGERS);
		}

		List<Passenger> passengersList = storePassenger(passengersCount);

		Bill.printBill(busDetails, availableBuses, boardingStation, destinationStation, passengersList,
				LocalDate.parse(date));

		while (true) {
			System.out.println("\nChoose From Below Options : \n1 Confirm Booking\n2 Cancel Booking");
			switch (sc.nextLine()) {
			case "1":
				isHuman();
				BookingDAO.getBookingDaoObject().save(new Booking(busNum, boardingStation, destinationStation,
						passengersCount, LocalDate.parse(date), UserLogin.email, passengersList));
				Bus bus = BusDAO.getBusDaoObject().get(busNum);
				bus.setVacancies(bus.getVacancies() - passengersCount);
				BusDAO.getBusDaoObject().update(bus);
				System.out.println(Constants.BOOKING_CONFIRMED);
				return true;
			case "2":
				System.out.println(Constants.BOOKING_DECLINED);
				return false;
			default:
				System.out.println(Constants.WRONG_CHOICE);
				break;
			}
		}
	}

	protected static int chooseValue(String message) {
		while (true) {
			try {
				System.out.println(message);
				return Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println(Constants.CHARACTERS);
			}
		}
	}

	protected static String chooseDate() {
		System.out.println(Constants.ENTER_DATE);
		String date = sc.nextLine();
		while (!isValidDate(date)) {
			System.out.println(Constants.ENTER_DATE);
			date = sc.nextLine();
		}
		return date;
	}

	private String chooseStation() {

		String station = sc.nextLine();
		while (!isValidStation(station)) {
			System.out.println(Constants.INVALID_STATION);
			station = sc.nextLine();
		}
		return station;
	}

	private static Set<String> getAllStations() {
		return StopsDAO.getStops();
	}

	private static void displayAllStations(Set<String> stations) {
		System.out.println(Constants.AVAILABLE_STATIONS);
		stations.forEach(System.out::println);
	}

	private boolean isValidStation(String station) {
		return stations.stream().anyMatch(station::equalsIgnoreCase);
	}

	protected static boolean isValidDate(String date) {
		try {
			LocalDate inputDate = LocalDate.parse(date);
			if (inputDate.isBefore(LocalDate.now())) {
				System.out.println(Constants.BEHIND);
				return false;
			}
			if (ChronoUnit.DAYS.between(LocalDate.now(), inputDate) > 90) {
				System.out.println(Constants.AHEAD);
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println(Constants.INVALID_DATE);
			return false;
		}
	}

	private static List<Object[]> getAvailableBuses(String boardingStation, String destinationStation) {

		return AvailableBusesDAO.getBuses(boardingStation, destinationStation);
	}

	private static void printBuses(List<Object[]> availableBuses) {
		for (Object[] availableBus : availableBuses) {
			Bus bus = BusDAO.getBusDaoObject().get((int) availableBus[0]);
			System.out.printf("Bus Number : %s\t%S\t-\t%S\tExpress\t%S\t%S%n%S\t-\t%S\t%7.2f%n%n", bus.getBusNumber(),
					bus.getSourceStation(), bus.getDestStation(), bus.getBusType(), bus.getCategory(), availableBus[1],
					availableBus[3], getPrice(bus, ((int) availableBus[4] - (int) availableBus[2])));
		}
	}

	protected static double getPrice(Bus busDetails, int distance) {
		if (busDetails.getBusType().equalsIgnoreCase("AC")) {
			if (busDetails.getCategory().equalsIgnoreCase("Seater"))
				return (distance * 3.5);
			else
				return (distance * 5.5);
		} else {
			if (busDetails.getCategory().equalsIgnoreCase("Seater"))
				return (distance * 1.5);
			else
				return (distance * 2.5);
		}
	}

	private static boolean isValidBusNumber(List<Object[]> availableBuses, int busNum) {
		for (Object[] bus : availableBuses) {
			if ((int) bus[0] == busNum)
				return true;
		}
		return false;
	}

	private static boolean isValidPassengerCount(Bus passengerBus, int passengersCount) {
		if (passengersCount > 6) {
			System.out.println(Constants.MAX_PASSENGERS);
			return false;
		} else if (passengersCount < 1) {
			System.out.println(Constants.MIN_PASSENGERS);
			return false;
		} else if (passengerBus.getVacancies() < passengersCount) {
			System.out.println("Sorry We Have Only " + passengerBus.getVacancies() + " Seats Left :)");
			return false;
		}
		return true;
	}

	private static List<Passenger> storePassenger(int passengerCount) {

		List<Passenger> passengerList = new ArrayList<>(passengerCount);
		for (int i = 1; i <= passengerCount; i++) {
			System.out.println(Constants.ENTER + i + " Passenger Name : ");
			String name = UserSignUp.getSignUpObject().storeName();
			System.out.println(Constants.ENTER + i + " Passenger Gender :");
			String gender = UserSignUp.getSignUpObject().storeGender();
			int age = chooseValue(Constants.ENTER + i + " Passenger Age : ");
			passengerList.add(new Passenger(name, age, gender));
		}
		return passengerList;
	}

	protected static boolean isWeekend(LocalDate date) {
		return (date.getDayOfWeek().toString().equalsIgnoreCase("SATURDAY")
				|| date.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY"));
	}

	private static void isHuman() {

		String captcha = generateCaptcha();
		System.out.println("Captcha : - " + captcha);
		System.out.println(Constants.ENTER_CAPTCHA);
		while (!captcha.equals(sc.nextLine())) {
			System.out.println(Constants.INCORRECT_CAPTCHA);
			captcha = generateCaptcha();
			System.out.println("Captcha : - " + captcha);
			System.out.println(Constants.ENTER_CAPTCHA);
		}
	}

	private static String generateCaptcha() {
		Random rand = new Random();
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder captcha = new StringBuilder();
		for (int i = 1; i <= 6; i++) {
			captcha.append(chars.charAt(rand.nextInt(61)));
		}
		return captcha.toString();
	}
}
