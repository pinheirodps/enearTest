package com.hooyu.exercise.repository;


import com.hooyu.exercise.customers.domain.Customer;
import com.hooyu.exercise.repository.generic.GenericRepository;
import org.springframework.stereotype.Component;


@Component
public interface Customers extends GenericRepository<Customer> {

}
