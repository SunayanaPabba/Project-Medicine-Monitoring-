package com.medicinemonitoring.medicinemonitoring.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medicinemonitoring.medicinemonitoring.dao.CustomerDao;
import com.medicinemonitoring.medicinemonitoring.dao.CustomerOrdersDao;
import com.medicinemonitoring.medicinemonitoring.dao.HelpDao;
import com.medicinemonitoring.medicinemonitoring.dao.MedicineDao;
import com.medicinemonitoring.medicinemonitoring.dao.PaymentDao;
import com.medicinemonitoring.medicinemonitoring.models.Admin;
import com.medicinemonitoring.medicinemonitoring.models.Customer;
import com.medicinemonitoring.medicinemonitoring.models.CustomerLogin;
import com.medicinemonitoring.medicinemonitoring.models.CustomerOrders;
import com.medicinemonitoring.medicinemonitoring.models.Help;
import com.medicinemonitoring.medicinemonitoring.models.Medicine;
import com.medicinemonitoring.medicinemonitoring.models.Payment;

@Component
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao cdao;
	@Autowired
	private MedicineDao mdao;
	@Autowired
	private CustomerOrdersDao codao;
	@Autowired
	private PaymentDao pdao;
	@Autowired
	private HelpDao hdao;
	@Override
	public int createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Customer cust=cdao.findByCustomerId(customer.getCustomerId());
		if(cust!=null) {
			return 1;
		}else {
			Customer cust1=cdao.save(customer);
			if(cust1!=null) {
				return 2;
			}
			else {
				return 3;
			}
		}

	}

	@Override
	public boolean loginVerification(CustomerLogin customerlogin) {
		// TODO Auto-generated method stub
		Customer customer=cdao.findByCustomerId(customerlogin.getUserId());
		if(customer!=null) {
			if(customerlogin.getPassword().equals(customer.getPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Medicine> medicineList() {
		// TODO Auto-generated method stub
		List<Medicine> list=(List<Medicine>) mdao.findAll();
		return list;
	}

	@Override
	public List<Medicine> medicineListByName(String medicinename) {
		// TODO Auto-generated method stub
		List<Medicine> list=mdao.getMedicineByName(medicinename);
		return list;
	}

	@Override
	public List<Medicine> medicineListByType(String type) {
		// TODO Auto-generated method stub
		List<Medicine> list=mdao.getMedicineByType(type);
		return list;
	}

	@Override
	public List<Medicine> medicineListByIllness(String medicinefor) {
		// TODO Auto-generated method stub
		List<Medicine> list=mdao.getMedicineByIllness(medicinefor);
		return list;
	}

	@Override
	public List<Medicine> medicineListByBrand(String manufacturer) {
		// TODO Auto-generated method stub
		List<Medicine> list=mdao.getMedicineByBrand(manufacturer);
		return list;
	}

	@Override
	public Medicine getMedicineById(int medid) {
		// TODO Auto-generated method stub
		Medicine med=mdao.findByMedicineId(medid);
		return med;
	}

	@Override
	public boolean saveOrder(String customerid, CustomerOrders custorder) {
		// TODO Auto-generated method stub
		Customer cust=cdao.findByCustomerId(customerid);
		custorder.setCustomer(cust);
		Date date=new Date();
		custorder.setOrderDate(date);
		CustomerOrders custorder1=codao.save(custorder);
		if(custorder1!=null) {
			return true;
		}
		return false;
	}

	@Override
	public Payment getPaymentDetails(long cardnumber) {
		// TODO Auto-generated method stub
		Payment paymentdetails=pdao.findByCardnumber(cardnumber);
		return paymentdetails;
	}

	@Override
	public boolean updateMedicineQuantity(int medicineid,int quantity) {
		// TODO Auto-generated method stub
		Medicine med=mdao.findByMedicineId(medicineid);
		int fullquan=med.getQuantity();
		med.setQuantity(fullquan-quantity);
		Medicine med1=mdao.save(med);
		if(med1!=null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Medicine> medicineListByAll(String searchname) {
		// TODO Auto-generated method stub
		List<Medicine> medlist=mdao.getMedicineByAll(searchname);
		return medlist;
	}

	@Override
	public List<Medicine> sortByName(List<Medicine> list) {
		// TODO Auto-generated method stub
		Comparator<Medicine> med=(m1,m2)->{
			return m1.getMedicineName().compareTo(m2.getMedicineName());
		};
		Collections.sort(list, med);
		return list;
	}

	@Override
	public List<Medicine> sortByType(List<Medicine> list) {
		// TODO Auto-generated method stub
		Comparator<Medicine> med=(m1,m2)->{
			return m1.getType().compareTo(m2.getType());
		};
		Collections.sort(list, med);
		return list;

	}

	@Override
	public List<Medicine> sortByBrand(List<Medicine> list) {
		// TODO Auto-generated method stub
		Comparator<Medicine> med=(m1,m2)->{
			return m1.getManufacturer().compareTo(m2.getManufacturer());
		};
		Collections.sort(list, med);
		return list;
	}

	@Override
	public List<Medicine> sortByIllness(List<Medicine> list) {
		// TODO Auto-generated method stub
		Comparator<Medicine> med=(m1,m2)->{
			return m1.getMedicineFor().compareTo(m2.getType());
		};
		Collections.sort(list, med);
		return list;
	}
	@Override
	public int reportIssue(Help h,String custname){
		Customer cust=cdao.findByCustomerId(custname);
		h.setCustomer(cust);
		Help h1=hdao.save(h);
		if(h1!=null)
		{
			return 1;
		}
		else
		{
		return 2;
		}
	}
	
	@Override
	public Customer getCustomerByContactNumber(String contactNumber) {
		return cdao.findByContactNumber(contactNumber); 
	}


	@Override
	public boolean updatePassword(Customer customer,String pwd) {
		customer.setPassword(pwd);
		Customer customer1 = cdao.save(customer);
		
		if(customer1!=null) {
			
			return true;
		}
		else {
			return false;
		}	}

	@Override
	public Customer getCustomerById(String userId) {
		 return cdao.findByCustomerId(userId);
	}

	@Override
	public List<Help> getHelpListByCustomerId(String userId) {
		// TODO Auto-generated method stub
		Customer customer=cdao.findByCustomerId(userId);
		List<Help> helplist=hdao.getHelpListByCustomerId(customer);
		return helplist;
	}

	@Override
	public List<CustomerOrders> getOrderlistByCustomerId(String userId) {
		// TODO Auto-generated method stub
		Customer customer=cdao.findByCustomerId(userId);
		List<CustomerOrders> list=codao.getOrderByCustomer(customer);
		return list;
	}

}


