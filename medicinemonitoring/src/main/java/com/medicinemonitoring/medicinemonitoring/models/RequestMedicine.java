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
@Table(name="requestmedicine")
public class RequestMedicine {
	@Id
	@Column
	@SequenceGenerator(name="reqseq", initialValue=101,sequenceName="Request_SEQUENCE", allocationSize=1)
	@GeneratedValue(generator="reqseq", strategy=GenerationType.SEQUENCE)
	private int token;
	@Column
	private String medicineName;
	@Column
	private String manufacturer;
	@Column
	private String illness;
	@Column
	private String quantity;
	@Column
	private String status;
	@ManyToOne
	private BranchAdmin branchadmin;
	public int getToken() {
		return token;
	}
	public void setToken(int token) {
		this.token = token;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getIllness() {
		return illness;
	}
	public void setIllness(String illness) {
		this.illness = illness;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BranchAdmin getBranchadmin() {
		return branchadmin;
	}
	public void setBranchadmin(BranchAdmin branchadmin) {
		this.branchadmin = branchadmin;
	}
	@Override
	public String toString() {
		return "RequestMedicine [token=" + token + ", medicineName=" + medicineName + ", manufacturer=" + manufacturer
				+ ", illness=" + illness + ", Quantity=" + quantity + ", status=" + status + ", branchadmin="
				+ branchadmin + "]";
	}
	
}
