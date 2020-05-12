package com.medicinemonitoring.medicinemonitoring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medicinemonitoring.medicinemonitoring.models.Payment;

@Repository
public interface PaymentDao extends CrudRepository<Payment, Long> {
	public Payment findByCardnumber(long cardnumber);

}
