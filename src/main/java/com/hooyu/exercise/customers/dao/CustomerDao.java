package com.hooyu.exercise.customers.dao;

import com.hooyu.exercise.customers.domain.Customer;

public interface CustomerDao {

	/**
	 * 
	 * @param email
	 * @return
	 * @throws CustomerNotFoundException
	 */
	Customer findCustomerByEmailAddress(String email) throws CustomerNotFoundException;
}
