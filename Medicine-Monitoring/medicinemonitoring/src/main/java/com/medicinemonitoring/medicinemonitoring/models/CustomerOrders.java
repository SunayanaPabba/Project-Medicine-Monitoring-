package com.medicinemonitoring.medicinemonitoring.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="customerorder")
public class CustomerOrders {
	@Id
	@SequenceGenerator(name="customerorderseq", initialValue=101,sequenceName="CUSTOMERORDER_SEQUENCE", allocationSize=1)
	@GeneratedValue(generator="customerorderseq", strategy=GenerationType.SEQUENCE)
	private int orderid;
	@Column
	private int medicineid;
	@Column
	private int Quantity;
	@Column
	private String address;
	@ManyToOne
	private Customer customer;
	@Column
	private String paymentmode;
	@Column
	private Date orderDate;
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getMedicineid() {
		return medicineid;
	}
	public void setMedicineid(int medicineid) {
		this.medicineid = medicineid;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate =new java.sql.Date(orderDate.getTime());
	}
	@Override
	public String toString() {
		return "CustomerOrders [orderid=" + orderid + ", medicineid=" + medicineid + ", Quantity=" + Quantity
				+ ", address=" + address + ", customer=" + customer + ", paymentmode=" + paymentmode + ", orderDate="
				+ orderDate + "]";
	}
	
	
}
