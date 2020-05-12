package com.medicinemonitoring.medicinemonitoring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicinemonitoring.medicinemonitoring.models.Customer;
import com.medicinemonitoring.medicinemonitoring.models.Help;

@Repository
public interface HelpDao extends CrudRepository<Help, Integer> {
	public Help findByToken(int token);
	@Query("Select h from Help h where h.status='new'" )
	public List<Help> getNewIssueList();
	@Query("Select h from Help h where h.customer=:customer and h.status='resolved'")
	public List<Help> getHelpListByCustomerId(@Param("customer") Customer cust);

}
