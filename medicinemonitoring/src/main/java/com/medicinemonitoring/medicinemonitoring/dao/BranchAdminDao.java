package com.medicinemonitoring.medicinemonitoring.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicinemonitoring.medicinemonitoring.models.BranchAdmin;


@Repository
public interface BranchAdminDao extends CrudRepository<BranchAdmin, String> {
	public BranchAdmin findByBranchId(String branchId);
	public BranchAdmin findByAdminId(String adminId);
	public BranchAdmin findByContactNumber(String contactNumber);
	

}
