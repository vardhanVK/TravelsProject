package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "buses")
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BusNumber")
	private int busNumber;

	@Column(name = "Starting_Station")
	private String sourceStation;

	@Column(name = "Ending_Station")
	private String destStation;

	@Column(name = "Type")
	private String busType;

	@Column(name = "Category")
	private String category;

	@Column(name = "Vacancies")
	private int vacancies;

	public Bus(int busNumber, String sourceStation, String destStation, String busType, String category,
			int vacancies) {
		super();
		this.busNumber = busNumber;
		this.sourceStation = sourceStation;
		this.destStation = destStation;
		this.busType = busType;
		this.category = category;
		this.vacancies = vacancies;
	}

	public Bus() {
	}

	public int getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(int busNumber) {
		this.busNumber = busNumber;
	}

	public String getSourceStation() {
		return sourceStation;
	}

	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}

	public String getDestStation() {
		return destStation;
	}

	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getVacancies() {
		return vacancies;
	}

	public void setVacancies(int vacancies) {
		this.vacancies = vacancies;
	}

	@Override
	public String toString() {
		return "Bus [busNumber=" + busNumber + ", sourceStation=" + sourceStation + ", destStation=" + destStation
				+ ", busType=" + busType + ", category=" + category + ", vacancies=" + vacancies + "]";
	}

}
