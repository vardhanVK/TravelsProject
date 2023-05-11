package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserAccount {
	
	@Id
	@Column(name = "email")
	private String emailId;
	
	@Column(name = "First_Name")
	private String firstName;
	
	@Column(name="Last_Name")
	private String lastName;
	
	@Column(name="Mobile_Number")
	private String mobileNumber;
	
	@Column(name="Gender")
	private String gender;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Failed_Count")
	private int failedCount;
	
	@Column(name="status")
	private String accountStatus;
	
	public UserAccount() {
	}
	
	public UserAccount(String firstName, String lastName, String mobileNumber, String gender, String emailId,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.emailId = emailId;
		this.password = password;
		this.failedCount = 0;
		this.accountStatus = "Active";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getFailedCount() {
		return failedCount;
	}

	public void setFailedCount(int failedCount) {
		this.failedCount = failedCount;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public String toString() {
		return "UserAccount [FirstName=" + firstName + ", LastName=" + lastName + ", MobileNumber=" + mobileNumber
				+ ", Gender=" + gender + ", EmailId=" + emailId + ", Password=" + password + ", FailedCount="
				+ failedCount + ", AccountStatus=" + accountStatus + "]";
	}
	
}
