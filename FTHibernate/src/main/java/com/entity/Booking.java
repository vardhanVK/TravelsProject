package com.entity;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Booking_Id")
	private int bookingId;
	
	@Column(name = "Bus_Number")
	private int busNumber;
	
	@Column(name = "Boarding_Station")
	private String boardingStation;
	
	@Column(name = "Dest_Station")
	private String destinationStation;
	
	@Column(name = "Passengers")
	private int passengers;
	
	@Column(name = "Travel_Date")
	private LocalDate date;
	
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Booking_Id")
	private List<Passenger> passengersList;
	
	public Booking() {
	}
	
	public Booking(int busNumber, String boardingStation, String destinationStation, int passengers,
			LocalDate date, String email, List<Passenger> passengersList) {
		this.busNumber = busNumber;
		this.boardingStation = boardingStation;
		this.destinationStation = destinationStation;
		this.passengers = passengers;
		this.date = date;
		this.email = email;
		this.passengersList = passengersList;
	}

	// write a constructor with booking id
	
	public int getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(int busNumber) {
		this.busNumber = busNumber;
	}

	public String getBoardingStation() {
		return boardingStation;
	}

	public void setBoardingStation(String boardingStation) {
		this.boardingStation = boardingStation;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Passenger> getPassengersList() {
		return passengersList;
	}

	public void setPassengersList(List<Passenger> passengersList) {
		this.passengersList = passengersList;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", busNumber=" + busNumber + ", boardingStation=" + boardingStation
				+ ", destinationStation=" + destinationStation + ", passengers=" + passengers + ", date=" + date
				+ ", email=" + email + ", passengersList=" + passengersList + "]";
	}
	
	
}
