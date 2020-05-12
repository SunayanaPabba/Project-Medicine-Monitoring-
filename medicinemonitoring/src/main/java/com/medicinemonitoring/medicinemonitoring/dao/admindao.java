package com.medicinemonitoring.medicinemonitoring.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicinemonitoring.medicinemonitoring.models.Admin;



@Repository
public interface admindao extends CrudRepository<Admin, String> {
	public Admin findByFirstName(String FirstName);
	public Admin findByAdminId(String adminId);
	public Admin findByContactNumber(String contactNumber);
	 

}
