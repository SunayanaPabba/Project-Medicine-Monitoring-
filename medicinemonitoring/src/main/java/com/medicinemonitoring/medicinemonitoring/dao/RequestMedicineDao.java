package com.medicinemonitoring.medicinemonitoring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.medicinemonitoring.medicinemonitoring.models.BranchAdmin;
import com.medicinemonitoring.medicinemonitoring.models.RequestMedicine;

public interface RequestMedicineDao extends CrudRepository<RequestMedicine, Integer> {
	public RequestMedicine findByToken(int token);
	
	@Query("Select s from RequestMedicine s where s.branchadmin=:badmin")
	public List<RequestMedicine> getMedicineRequestByBranchId(@Param("badmin") BranchAdmin badmin);
}
