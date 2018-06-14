package com.hooyu.exercise.customers.service;

import com.hooyu.exercise.controllers.EmailAlreadyExistsInSessionException;
import com.hooyu.exercise.customers.dao.CustomerNotFoundException;
import com.hooyu.exercise.customers.domain.Customer;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


public interface CustomerService {
    Customer findCustomerByEmailAddress(Customer customerParam) throws CustomerNotFoundException;
    void checkEmailInSession(String email,  HttpSession session) throws EmailAlreadyExistsInSessionException;
    List<Customer> filterCustomerByFields(Customer customerFilter);

}
