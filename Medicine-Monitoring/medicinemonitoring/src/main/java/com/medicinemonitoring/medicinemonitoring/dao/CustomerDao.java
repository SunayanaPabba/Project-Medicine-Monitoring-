package com.medicinemonitoring.medicinemonitoring.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicinemonitoring.medicinemonitoring.models.Customer;


@Repository
public interface CustomerDao extends CrudRepository<Customer, String> {
	public Customer findByCustomerId(String customerId);
	public Customer findByContactNumber(String contactNumber);
	
}
