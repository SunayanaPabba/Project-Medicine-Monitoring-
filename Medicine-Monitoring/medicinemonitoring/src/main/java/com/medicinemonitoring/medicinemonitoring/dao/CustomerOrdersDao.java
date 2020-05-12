package com.medicinemonitoring.medicinemonitoring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicinemonitoring.medicinemonitoring.models.Customer;
import com.medicinemonitoring.medicinemonitoring.models.CustomerOrders;

@Repository
public interface CustomerOrdersDao extends CrudRepository<CustomerOrders, Integer> {
	public CustomerOrders findByOrderid(int orderid);
	@Query("Select c from CustomerOrders c where c.customer=:customer")
	public List<CustomerOrders> getOrderByCustomer(@Param("customer") Customer customer);
}
