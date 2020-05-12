package com.medicinemonitoring.medicinemonitoring.models;

import javax.persistence.Entity;

import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.*;
@Entity
@Table(name="customer")
public class Customer {
	@Id
	@Generated("assigned")
	@Column
  private String customerId;
	@Column
  private String password;
	@Column
  private String firstName;
	@Column
  private String lastName;
	@Column
  private Date dob;
	@Column
  private String gender;
	@Column
  private String contactNumber;
	@Column
	private String email;
	@Column
  private String secretQuestion;
	@Column
  private String answer;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSecretQuestion() {
		return secretQuestion;
	}
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender + ", contactNumber=" + contactNumber
				+ ", email=" + email + ", secretQuestion=" + secretQuestion + ", answer=" + answer + "]";
	}
  
}
