package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "passengers")
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Passenger_Id")
	private int passengerId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Age")
	private int age;

	@Column(name = "Gender")
	private String gender;
	
	public Passenger() {
	}
	
	public Passenger(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
	
	
}
