package com.hooyu.exercise.controllers

import com.hooyu.exercise.customers.domain.Customer
import com.hooyu.exercise.customers.domain.CustomerBuilder
import com.hooyu.exercise.customers.domain.CustomerType
import com.hooyu.exercise.customers.service.CustormerServiceImpl
import com.hooyu.exercise.repository.impl.CustomerRepository
import org.springframework.mock.web.MockHttpSession
import spock.lang.Specification

import java.util.stream.Collectors

class SignlnControllerTest extends Specification {


     CustormerServiceImpl customerService
     CustomerRepository customers

    SignInController controller
     MockHttpSession session



    def setup() {
        controller = new SignInController()
        customerService = new CustormerServiceImpl()
        customers = new CustomerRepository()
        session = new MockHttpSession()
    }

    def "should throw EmailAlreadyExistsInSessionException"() {
        when:
        session.setAttribute("email", "john.doe@192.com")
        customerService.checkEmailInSession("john.doe@192.com",session )

        then:
        thrown(EmailAlreadyExistsInSessionException.class)
    }

    def "should return expected ListCustomer"() {
        when:
         Map<String,Customer> customerList = customers.findAll()

        then:
        customerList.equals(getExpectedListCustomer())
    }

    def "should return expected filterCustomerByFields"() {
        when:
        List<Customer>  customerFilterList = customers.filterByFields(filterCustomer())

        then:
        customerFilterList.equals(getExpectedListFilterCustomer())
    }


    def "should return expected Customer"() {
        when:
        Customer customer =  getExpectedListCustomer().get("john.doe@192.com")

        then:
        customer.equals(getExpectedCustomer())
    }

    def getExpectedListCustomer() {
        final Map<String,Customer> customers = new HashMap<>();
        customers.put("john.doe@192.com",new CustomerBuilder().setEmailAddress("john.doe@192.com").setForename("John").setSurname("Doe").setCustomerType(CustomerType.PREMIUM).setPostCode("123").builder());
        customers.put("sally.smith@192.com",new CustomerBuilder().setEmailAddress("sally.smith@192.com").setForename("Sally").setSurname("Smith").setCustomerType(CustomerType.PREMIUM).setPostCode("456").builder());
        customers.put("harry.lang@192.com",new CustomerBuilder().setEmailAddress("harry.lang@192.com").setForename("Harry").setSurname("Lang").setCustomerType(CustomerType.NON_PAYING).setPostCode("789").builder());

        return customers
    }

    def getExpectedCustomer() {
        Customer expectedCustomer = new Customer();
        expectedCustomer.setEmailAddress("john.doe@192.com")
        expectedCustomer.setForename("John")
        expectedCustomer.setSurname("Doe")
        expectedCustomer.setCustomType(CustomerType.PREMIUM)
        expectedCustomer.setPostCode("123")
        return expectedCustomer
    }

    def filterCustomer() {
        Customer expectedCustomer = new Customer()
        expectedCustomer.setSurname("Doe")
        expectedCustomer.setPostCode("123")
        return expectedCustomer
    }

    def getExpectedListFilterCustomer() {
        final Map<String,Customer> customers = new HashMap<>();
        customers.put("john.doe@192.com",new CustomerBuilder().setEmailAddress("john.doe@192.com").setForename("John").setSurname("Doe").setCustomerType(CustomerType.PREMIUM).setPostCode("123").builder());

        return customers.values().stream().collect(Collectors.toList())
    }


}
