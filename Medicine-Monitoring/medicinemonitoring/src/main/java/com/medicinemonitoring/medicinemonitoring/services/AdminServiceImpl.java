package com.medicinemonitoring.medicinemonitoring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medicinemonitoring.medicinemonitoring.dao.BranchAdminDao;
import com.medicinemonitoring.medicinemonitoring.dao.CustomerOrdersDao;
import com.medicinemonitoring.medicinemonitoring.dao.HelpDao;
import com.medicinemonitoring.medicinemonitoring.dao.MedicineDao;
import com.medicinemonitoring.medicinemonitoring.dao.RequestMedicineDao;
import com.medicinemonitoring.medicinemonitoring.dao.admindao;
import com.medicinemonitoring.medicinemonitoring.models.Admin;
import com.medicinemonitoring.medicinemonitoring.models.AdminLogin;
import com.medicinemonitoring.medicinemonitoring.models.BranchAdmin;
import com.medicinemonitoring.medicinemonitoring.models.CustomerOrders;
import com.medicinemonitoring.medicinemonitoring.models.Help;
import com.medicinemonitoring.medicinemonitoring.models.Medicine;
import com.medicinemonitoring.medicinemonitoring.models.RequestMedicine;

@Component
public class AdminServiceImpl implements AdminService{
	@Autowired
	private admindao dao;
	@Autowired
	private BranchAdminDao bdao;
	@Autowired
	private MedicineDao mdao;
	@Autowired
	private RequestMedicineDao rmdao;
	@Autowired
	private HelpDao hdao;
	@Autowired
	private CustomerOrdersDao codao;
	@Override
	public int createAdmin(Admin admin) {
		// TODO Auto-generated method stub
		Admin ad=dao.findByAdminId(admin.getAdminId());
		if(ad!=null) {
			return 1;
		}else {
			Admin ad1=dao.save(admin);
			if(ad1!=null) {
				return 2;
			}
			else {
				return 3;
			}
		}
		
	}

	@Override
	public boolean loginVerification(AdminLogin adminlogin) {
		// TODO Auto-generated method stub
		Admin admin=dao.findByAdminId(adminlogin.getUserId());
		if(admin!=null) {
			if(adminlogin.getPassword().equals(admin.getPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<BranchAdmin> requestlist() {
		// TODO Auto-generated method stub
		List<BranchAdmin> list=(List<BranchAdmin>) bdao.findAll();
		return list;
	}

	@Override
	public boolean acceptRequest(String id) {
		// TODO Auto-generated method stub
		BranchAdmin badmin=bdao.findByBranchId(id);
		System.out.println(badmin);
		String status=badmin.getStatus();
		badmin.setStatus("Yes");
		bdao.save(badmin);
		if(badmin.getStatus().equalsIgnoreCase(status)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean rejectRequest(String id) {
		// TODO Auto-generated method stub
		BranchAdmin badmin=bdao.findByBranchId(id);
		String status=badmin.getStatus();
		badmin.setStatus("No");
		bdao.save(badmin);
		if(badmin.getStatus().equalsIgnoreCase(status)) {
			return true;
		}
		return false;

	}
	@Override
	public boolean addMedicine(Medicine addmed) {
		Medicine am=mdao.save(addmed);
		if(am!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<Medicine> medicineList() {
		// TODO Auto-generated method stub
		Iterable<Medicine> list=mdao.findAll();
		if(list==null) {
			return null;
		}else {
			return (List<Medicine>) list;
		}
		
	}

	@Override
	public Medicine getMedicineById(int medicineId) {
		// TODO Auto-generated method stub
		Medicine med=mdao.findById(medicineId).get();
		return med;
	}

	@Override
	public boolean updateMedicineDetails(int medicineId, Medicine medicine) {
		// TODO Auto-generated method stub
		Medicine med=mdao.findByMedicineId(medicineId);
		med.setMedicineName(medicine.getMedicineName());
		med.setMedicineFor(medicine.getMedicineFor());
		med.setManufacturer(medicine.getManufacturer());
		med.setExpiryDate(medicine.getExpiryDate());
		med.setMfgDate(medicine.getMfgDate());
		med.setMrp(medicine.getMrp());
		med.setQuantity(medicine.getQuantity());
		Medicine m=mdao.save(med);
		if(m!=null) {
			return true;
		}
		return false;
	}

	@Override
	public List<BranchAdmin> branchlist() {
		// TODO Auto-generated method stub
		Iterable<BranchAdmin> list=bdao.findAll();
		if(list==null) {
			return null;
		}else {
		return (List<BranchAdmin>) list;
	}

}

	@Override
	public List<Medicine> getMedicineByType(String branchid, String type) {
		// TODO Auto-generated method stub
		BranchAdmin badmin=bdao.findByBranchId(branchid);
		List<Medicine> medicinelist=mdao.getByMedicineType(badmin, type);
		return medicinelist;
	}

	@Override
	public List<Medicine> getBranchMedicineList(String branchid) {
		// TODO Auto-generated method stub
		BranchAdmin badmin=bdao.findByBranchId(branchid);
		Iterable<Medicine> list=mdao.getMedicineByBranchId(badmin);
		if(list==null) {
			return null;
		}else {
		return (List<Medicine>) list;
		}
	}

	@Override
	public List<RequestMedicine> getMedicineRequestList() {
		// TODO Auto-generated method stub
		Iterable<RequestMedicine> reqmedlist=rmdao.findAll();
		if(reqmedlist==null) {
			return null;
		}else {
			return (List<RequestMedicine>) reqmedlist;
		}
	
	}

	@Override
	public boolean approveMedicineRequest(int token) {
		// TODO Auto-generated method stub
		RequestMedicine rmed=rmdao.findByToken(token);
		rmed.setStatus("Approved");
		RequestMedicine rmed1=rmdao.save(rmed);
		if(rmed1!=null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean denyMedicineRequest(int token) {
		// TODO Auto-generated method stub
		RequestMedicine rmed=rmdao.findByToken(token);
		rmed.setStatus("Denied");
		RequestMedicine rmed1=rmdao.save(rmed);
		if(rmed1!=null) {
			return true;
		}
		return false;

		
	}
	
	
	@Override
	public List<Help> viewIssues() {
		// TODO Auto-generated method stub
		Iterable<Help> list=hdao.getNewIssueList();
		if(list==null) {
		return null;
		}else {
			return (List<Help>) list;
		}
	}

	@Override
	public RequestMedicine getRequestByToken(int token) {
		// TODO Auto-generated method stub
		RequestMedicine t=rmdao.findByToken(token);
		return t;
	}

	@Override
	public Help getIssueByToken(int token) {
		// TODO Auto-generated method stub
		Help h=hdao.findByToken(token);
		return h;
	}

	@Override
	public boolean updateIssueResolution(int token, String resolution) {
		// TODO Auto-generated method stub
		Help h=hdao.findByToken(token);
		h.setResolution(resolution);
		h.setStatus("resolved");
		Help h1=hdao.save(h);
		if(h1!=null) {
			return true;
		}
		return false;
	}

	 @Override 
	  public Admin getAdminByContactNumber(String contactNumber) {
		 return dao.findByContactNumber(contactNumber); 
	  }
	  
	  
	 @Override 	
	public boolean updatePassword(Admin admin,String pwd) {
		 admin.setPassword(pwd);
		Admin admin1 = dao.save(admin);	
		if(admin1!=null) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	@Override 
	public Admin getAdminById(String userId) {
	  return dao.findByAdminId(userId);
	  }

	@Override
	public List<CustomerOrders> getCustomerOrderList() {
		// TODO Auto-generated method stub
		List<CustomerOrders> list=(List<CustomerOrders>) codao.findAll();
		return list;
	}

	
}
