package com.medicinemonitoring.medicinemonitoring.services;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.medicinemonitoring.medicinemonitoring.models.Admin;
import com.medicinemonitoring.medicinemonitoring.models.AdminLogin;
import com.medicinemonitoring.medicinemonitoring.models.BranchAdmin;
import com.medicinemonitoring.medicinemonitoring.models.CustomerOrders;
import com.medicinemonitoring.medicinemonitoring.models.Help;
import com.medicinemonitoring.medicinemonitoring.models.Medicine;
import com.medicinemonitoring.medicinemonitoring.models.RequestMedicine;


public interface AdminService {
	public int createAdmin(Admin admin );
	public boolean loginVerification(AdminLogin adminlogin );
	public List<BranchAdmin> requestlist();
	public boolean acceptRequest(String username);
	public boolean rejectRequest(String username);
	boolean addMedicine(Medicine addmed);
	public List<Medicine> medicineList();
	public Medicine getMedicineById(int medicineId);
	public boolean updateMedicineDetails(int medicineId,Medicine medicine);
	public List<BranchAdmin> branchlist();
	public List<Medicine> getMedicineByType(String branchid,String type);
	public List<Medicine> getBranchMedicineList(String branchid);
	public List<RequestMedicine> getMedicineRequestList();
	public RequestMedicine getRequestByToken(int token);
	public boolean approveMedicineRequest(int token);
	public boolean denyMedicineRequest(int token);
	public List<Help> viewIssues();
	public Help getIssueByToken(int token);
	public boolean updateIssueResolution(int token,String resolution);
	public Admin getAdminById(String userId);
	public Admin getAdminByContactNumber(String contactNumber);
	public boolean updatePassword(Admin admin,String pwd);
	public List<CustomerOrders> getCustomerOrderList();
}
