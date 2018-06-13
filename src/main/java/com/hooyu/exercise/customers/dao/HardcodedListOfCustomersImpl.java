package com.hooyu.exercise.customers.dao;

import com.hooyu.exercise.customers.domain.Customer;
import com.hooyu.exercise.customers.domain.CustomerType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HardcodedListOfCustomersImpl implements CustomerDao {

	private static Map<String,Customer> customers = new HashMap<>();
	
	public HardcodedListOfCustomersImpl() {
		customers.put("john.doe@192.com", createDummyCustomer("john.doe@192.com", "John", "Doe", CustomerType.PREMIUM,"123"));
		customers.put("sally.smith@192.com", createDummyCustomer("sally.smith@192.com", "Sally", "Smith", CustomerType.PREMIUM, "456"));
		customers.put("harry.lang@192.com", createDummyCustomer("harry.lang@192.com", "Harry", "Lang", CustomerType.NON_PAYING, "789"));
	}
	
	public Customer findCustomerByEmailAddress(String email) throws CustomerNotFoundException {
		Customer customer = customers.get(email);
		if(customer == null) {
			throw new CustomerNotFoundException("Invalid customer");
		}	
		return customer;
	}
	
	private Customer createDummyCustomer(String email, String forename, String surname, CustomerType type, String postCode) {
		Customer c = new Customer();
		c.setEmailAddress(email);
		c.setForename(forename);
		c.setSurname(surname);
		c.setCustomType(type);
		c.setPostCode(postCode);
		return c;
	}
}