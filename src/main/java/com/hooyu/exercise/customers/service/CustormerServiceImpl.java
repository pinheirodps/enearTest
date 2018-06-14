package com.hooyu.exercise.customers.service;

import com.hooyu.exercise.controllers.EmailAlreadyExistsInSessionException;
import com.hooyu.exercise.customers.dao.CustomerNotFoundException;
import com.hooyu.exercise.customers.domain.Customer;
import com.hooyu.exercise.repository.impl.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CustormerServiceImpl implements CustomerService {


    @Autowired
    private CustomerRepository customers;

    private HttpSession session;

    @Autowired
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public Customer findCustomerByEmailAddress(Customer customerParam)throws CustomerNotFoundException {
        return customers.findByField(customerParam);
    }

    @Override
    public void checkEmailInSession(String email,  HttpSession session) throws EmailAlreadyExistsInSessionException {
        String emailSession = (String) session.getAttribute("email");
        if (email != null && email.equals(emailSession)){
            throw new EmailAlreadyExistsInSessionException("Email already exists in session!") ;
        }
    }

    public List<Customer> filterCustomerByFields(Customer customerFilter) {
        return customers.filterByFields(customerFilter);
    }
}
