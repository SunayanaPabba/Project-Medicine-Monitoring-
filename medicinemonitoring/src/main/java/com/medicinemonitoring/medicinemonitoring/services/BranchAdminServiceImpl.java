package com.medicinemonitoring.medicinemonitoring.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medicinemonitoring.medicinemonitoring.dao.BranchAdminDao;
import com.medicinemonitoring.medicinemonitoring.dao.CustomerOrdersDao;
import com.medicinemonitoring.medicinemonitoring.dao.MedicineDao;
import com.medicinemonitoring.medicinemonitoring.dao.RequestMedicineDao;
import com.medicinemonitoring.medicinemonitoring.models.BranchAdmin;
import com.medicinemonitoring.medicinemonitoring.models.BranchAdminLogin;
import com.medicinemonitoring.medicinemonitoring.models.CustomerOrders;
import com.medicinemonitoring.medicinemonitoring.models.Medicine;
import com.medicinemonitoring.medicinemonitoring.models.RequestMedicine;

@Component
public class BranchAdminServiceImpl implements BranchAdminService {
	@Autowired
	private BranchAdminDao bdao;
	@Autowired
	private MedicineDao mdao;
	@Autowired
	private RequestMedicineDao rmdao;
	@Autowired
	private CustomerOrdersDao codao;
	@Override
	public int createBranchAdmin(BranchAdmin branchadmin) {
		// TODO Auto-generated method stub
		BranchAdmin badmin=bdao.findByBranchId(branchadmin.getBranchId());
		if(badmin!=null) {
			return 1;
		}else if(bdao.findByAdminId((branchadmin.getAdminId())) != null){
			return 2;
		}else {
			branchadmin.setStatus("No");
			BranchAdmin badmin1=bdao.save(branchadmin);
			if(badmin1!=null) {
				return 3;
			}
			else {
				return 4;
			}
		}
		

	}

	@Override
	public int loginVerification(BranchAdminLogin branchadminlogin) {
		// TODO Auto-generated method stub
		BranchAdmin branchadmin=bdao.findByAdminId(branchadminlogin.getUserId());
		if(branchadmin!=null) {
			if(branchadminlogin.getPassword().equals(branchadmin.getPassword())&&branchadmin.getStatus().equalsIgnoreCase("yes")) {
				return 1;
			}else if(branchadmin.getPassword().equals(branchadminlogin.getPassword())&& branchadmin.getStatus().equalsIgnoreCase("no") ) {
				return 2;
			}		
		}
		return 3;
	}

	@Override
	public List<Medicine> MedicineList(String userid) {
		
		BranchAdmin ba = bdao.findByAdminId(userid);
		List<Medicine> medlist =mdao.getMedicineByBranchId(ba);
		
		return medlist;

	}

	@Override
	public boolean addBranchMedicine(Medicine addmed,String userid) {
		// TODO Auto-generated method stub 
		
		BranchAdmin ba=bdao.findByAdminId(userid);
		addmed.setBranchadmin(ba);
		Medicine m =mdao.save(addmed);
		 if(m!=null)
			 return true;
		 
		return false;

	}

	

	@Override
	public Medicine getBranchMedicineById(String userid,int medicineId) {
		// TODO Auto-generated method stub
		BranchAdmin ba = bdao.findByAdminId(userid);
		List<Medicine> medlist =mdao.getMedicineByBranchId(ba);
		for(Medicine med:medlist) {
			if(med.getMedicineId()==medicineId) {
				return med;
			}
		}
		return null;
	}

	
	@Override
	public boolean updateBranchMedicineDetails(String userid,int medicineId, Medicine medicine) {
		// TODO Auto-generated method stub
		BranchAdmin badmin=bdao.findByAdminId(userid);
		Medicine med=mdao.getBranchMedicineById(badmin, medicineId);
		med.setMedicineName(medicine.getMedicineName());
		med.setMedicineFor(medicine.getMedicineFor());
		med.setManufacturer(medicine.getManufacturer());
		med.setExpiryDate(medicine.getExpiryDate());
		med.setMfgDate(medicine.getMfgDate());
		med.setMrp(medicine.getMrp());
		med.setQuantity(medicine.getQuantity());
		Medicine med1=mdao.save(med);
		if(med1!=null) {
			return true;
		}
		return false;

	}

	@Override
	public boolean addRequest(String userid, RequestMedicine reqmed) {
		// TODO Auto-generated method stub
		BranchAdmin badmin=bdao.findByAdminId(userid);
		reqmed.setBranchadmin(badmin);
		reqmed.setStatus("requested");
		RequestMedicine rmed=rmdao.save(reqmed);
		if(rmed!=null) {
			return true;
		}
		return false;
	}
	
	
	@Override 
	  public BranchAdmin getBranchAdminByContactNumber(String contactNumber) {
		 return bdao.findByContactNumber(contactNumber); 
	  }
	  
	  
	 @Override 	
	public boolean updatePassword(BranchAdmin badmin, String pwd) {
		 badmin.setPassword(pwd);
		BranchAdmin badmin1 = bdao.save(badmin);	
		if(badmin1!=null) {
			return true;
		}
		else {
			return false;
		}	
	}
	 
	 
	@Override 
	public BranchAdmin getBranchAdminById(String userId) {
	  return bdao.findByAdminId(userId);
	  }

	@Override
	public List<CustomerOrders> getCustomerOrderList() {
		// TODO Auto-generated method stub
		List<CustomerOrders> list=(List<CustomerOrders>) codao.findAll();
		return list;
	}

	
	



}
