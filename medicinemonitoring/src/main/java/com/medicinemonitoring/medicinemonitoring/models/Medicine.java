package com.medicinemonitoring.medicinemonitoring.models;

import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="medicine")
public class Medicine implements Comparable<Medicine> {
	@Id
	@SequenceGenerator(name="medicineseq", initialValue=101,sequenceName="MEDICINE_SEQUENCE", allocationSize=1)
	@GeneratedValue(generator="medicineseq", strategy=GenerationType.SEQUENCE)
	private int medicineId;
	@Column
	private String medicineName;
	@Column
	private String medicineFor;
	@Column
	private String manufacturer;
	@Column
	private Date mfgDate;
	@Column
	private Date expiryDate;
	@Column
	private double mrp;
	@Column
	private int quantity;
	@Column
	private String type;
	@ManyToOne
	private BranchAdmin  branchadmin;
	public int getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getMedicineFor() {
		return medicineFor;
	}
	public void setMedicineFor(String medicineFor) {
		this.medicineFor = medicineFor;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Date getMfgDate() {
		return mfgDate;
	}
	public void setMfgDate(Date mfgDate){
		this.mfgDate = mfgDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public BranchAdmin getBranchadmin() {
		return branchadmin;
	}
	public void setBranchadmin(BranchAdmin branchadmin) {
		this.branchadmin = branchadmin;
	}
	@Override
	public String toString() {
		return "Medicine [medicineId=" + medicineId + ", medicineName=" + medicineName + ", medicineFor=" + medicineFor
				+ ", manufacturer=" + manufacturer + ", mfgDate=" + mfgDate + ", expiryDate=" + expiryDate + ", mrp="
				+ mrp + ", quantity=" + quantity + ", type=" + type + ", branchlist=" + branchadmin + "]";
	}
	
	@Override
	public int compareTo(Medicine o) {
		// TODO Auto-generated method stub
		if(this.medicineId>o.medicineId) {
			return 1;
		}else if(this.medicineId<o.medicineId) {
			return -1;
		}else {
		return 0;
		}
	}
	
}
