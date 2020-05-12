package com.medicinemonitoring.medicinemonitoring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="help")
public class Help {
	@Id
	@SequenceGenerator(name="helpseq", initialValue=301,sequenceName="HELP_SEQUENCE", allocationSize=1)
	@GeneratedValue(generator="helpseq", strategy=GenerationType.SEQUENCE)
   @Column
   private	int token;
   @Column
   private String issue;
   @Column 
   private String description;
   @Column
   private String contactNumber;
   @Column
   private String status="new";
   @ManyToOne
   private Customer customer;
   @Column
   private String resolution;
public int getToken() {
	return token;
}
public void setToken(int token) {
	this.token = token;
}
public String getIssue() {
	return issue;
}
public void setIssue(String issue) {
	this.issue = issue;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getContactNumber() {
	return contactNumber;
}
public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Customer getCustomer() {
	return customer;
}
public void setCustomer(Customer customer) {
	this.customer = customer;
}
public String getResolution() {
	return resolution;
}
public void setResolution(String resolution) {
	this.resolution = resolution;
}
@Override
public String toString() {
	return "Help [token=" + token + ", issue=" + issue + ", description=" + description + ", contactNumber="
			+ contactNumber + ", status=" + status + ", customer=" + customer + ", resolution=" + resolution + "]";
}
}