package com.medicinemonitoring.medicinemonitoring.models;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="payment")
public class Payment {
	@Id
	@Generated("assigned")
	@Column
	private long cardnumber;
	@Column
	private int cvv;
	@Column
	private Date ExpiryDate;
	@Column
	private String name;
	public long getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(long cardnumber) {
		this.cardnumber = cardnumber;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public Date getExpiryDate() {
		return ExpiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		ExpiryDate = expiryDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Payment [cardnumber=" + cardnumber + ", cvv=" + cvv + ", ExpiryDate=" + ExpiryDate + ", name=" + name
				+ "]";
	}
	
	
}
