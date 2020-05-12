package com.medicinemonitoring.medicinemonitoring.services;

import java.util.List;

import com.medicinemonitoring.medicinemonitoring.models.Customer;
import com.medicinemonitoring.medicinemonitoring.models.CustomerLogin;
import com.medicinemonitoring.medicinemonitoring.models.CustomerOrders;
import com.medicinemonitoring.medicinemonitoring.models.Help;
import com.medicinemonitoring.medicinemonitoring.models.Medicine;
import com.medicinemonitoring.medicinemonitoring.models.Payment;

public interface CustomerService {
	public int createCustomer(Customer customer );
	public boolean loginVerification(CustomerLogin customerlogin );
	public List<Medicine> medicineList();
	public List<Medicine> medicineListByAll(String searchname);
	public List<Medicine> medicineListByName(String medicinename);
	public List<Medicine> medicineListByType(String type);
	public List<Medicine> medicineListByIllness(String medicineFor);
	public List<Medicine> medicineListByBrand(String manufacturer);
	public Medicine getMedicineById(int medid);
	public boolean saveOrder(String customerid,CustomerOrders custorder);
	public Payment getPaymentDetails(long cardnumber);
	public boolean updateMedicineQuantity(int medicineid,int quantity);
	public List<Medicine> sortByName(List<Medicine> list);
	public List<Medicine> sortByType(List<Medicine> list);
	public List<Medicine> sortByBrand(List<Medicine> list);
	public List<Medicine> sortByIllness(List<Medicine> list);
	public  int reportIssue(Help h, String custname);
	public Customer getCustomerByContactNumber(String contactNumber);
	public boolean updatePassword(Customer customer,String pwd);
	public Customer getCustomerById(String userId);
	public List<Help> getHelpListByCustomerId(String userId);
	public List<CustomerOrders> getOrderlistByCustomerId(String userId);
}
