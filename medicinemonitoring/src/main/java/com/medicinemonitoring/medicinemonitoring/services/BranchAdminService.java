package com.medicinemonitoring.medicinemonitoring.services;

import java.util.List;
import com.medicinemonitoring.medicinemonitoring.models.BranchAdmin;
import com.medicinemonitoring.medicinemonitoring.models.BranchAdminLogin;
import com.medicinemonitoring.medicinemonitoring.models.CustomerOrders;
import com.medicinemonitoring.medicinemonitoring.models.Medicine;
import com.medicinemonitoring.medicinemonitoring.models.RequestMedicine;
public interface BranchAdminService {
	public int createBranchAdmin(BranchAdmin branchadmin );
	public int loginVerification(BranchAdminLogin branchadminlogin );
	public List<Medicine> MedicineList(String userid);
	boolean addBranchMedicine(Medicine addmed,String userid);
	public Medicine getBranchMedicineById(String userid,int medicineId);
	public boolean updateBranchMedicineDetails(String userid,int medicineId,Medicine medicine);
	public boolean addRequest(String userid,RequestMedicine reqmed);
	public BranchAdmin getBranchAdminById(String userId);
	public BranchAdmin getBranchAdminByContactNumber(String contactNumber);
	public boolean updatePassword(BranchAdmin badmin,String pwd);
	public List<CustomerOrders> getCustomerOrderList(); 
}
