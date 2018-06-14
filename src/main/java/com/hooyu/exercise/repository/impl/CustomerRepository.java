package com.hooyu.exercise.repository.impl;


import com.hooyu.exercise.customers.dao.CustomerNotFoundException;
import com.hooyu.exercise.customers.domain.Customer;
import com.hooyu.exercise.customers.domain.CustomerBuilder;
import com.hooyu.exercise.customers.domain.CustomerType;
import com.hooyu.exercise.repository.Customers;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomerRepository implements Customers {


    @Override
    public Customer findByField(Customer customerParam) {
        Customer customer =  findAll().get(customerParam.getEmailAddress());
        if(customer == null) {
            throw new CustomerNotFoundException("Invalid customer");
        }
        return customer;
    }


    @Override
    public List<Customer> filterByFields(Customer customerFilter) {
        final Map<String,Customer> customers = new HashMap<>();
        customers.put("john.doe@192.com",new CustomerBuilder().setEmailAddress("john.doe@192.com").setForename("John").setSurname("Doe").setCustomerType(CustomerType.PREMIUM).setPostCode("123").builder());
        customers.put("sally.smith@192.com",new CustomerBuilder().setEmailAddress("sally.smith@192.com").setForename("Sally").setSurname("Smith").setCustomerType(CustomerType.PREMIUM).setPostCode("456").builder());
        customers.put("harry.lang@192.com",new CustomerBuilder().setEmailAddress("harry.lang@192.com").setForename("Harry").setSurname("Lang").setCustomerType(CustomerType.NON_PAYING).setPostCode("789").builder());
        return customers
                .values().stream()
                .filter(c -> customerFilter.getSurname().equals(c.getSurname()))
                .filter(c -> customerFilter.getPostCode().equals(c.getPostCode()))
                .collect(Collectors.toList())
                ;
    }


    @Override
    public Map<String, Customer> findAll() {
        final Map<String,Customer> customers = new HashMap<>();
        customers.put("john.doe@192.com",new CustomerBuilder().setEmailAddress("john.doe@192.com").setForename("John").setSurname("Doe").setCustomerType(CustomerType.PREMIUM).setPostCode("123").builder());
        customers.put("sally.smith@192.com",new CustomerBuilder().setEmailAddress("sally.smith@192.com").setForename("Sally").setSurname("Smith").setCustomerType(CustomerType.PREMIUM).setPostCode("456").builder());
        customers.put("harry.lang@192.com",new CustomerBuilder().setEmailAddress("harry.lang@192.com").setForename("Harry").setSurname("Lang").setCustomerType(CustomerType.NON_PAYING).setPostCode("789").builder());
        return customers;
    }


}
