package com.hooyu.exercise.controllers

import com.hooyu.exercise.customers.domain.Customer
import com.hooyu.exercise.customers.domain.CustomerBuilder
import com.hooyu.exercise.customers.domain.CustomerType
import com.hooyu.exercise.customers.service.CustormerServiceImpl
import org.springframework.mock.web.MockHttpSession
import spock.lang.Specification

class SignlnControllerTest extends Specification {


     CustormerServiceImpl customerService
     SignInController controller
     MockHttpSession session



    def setup() {
        controller = new SignInController()
        customerService = new CustormerServiceImpl()
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
         Map<String,Customer> customerList = customerService.findAll()

        then:
        customerList.equals(getExpectedListCustomer())
    }

    def getExpectedListCustomer() {
        final Map<String,Customer> customers = new HashMap<>();
        customers.put("john.doe@192.com",new CustomerBuilder().setEmailAddress("john.doe@192.com").setForename("John").setSurname("Doe").setCustomerType(CustomerType.PREMIUM).setPostCode("123").builder());
        customers.put("sally.smith@192.com",new CustomerBuilder().setEmailAddress("sally.smith@192.com").setForename("Sally").setSurname("Smith").setCustomerType(CustomerType.PREMIUM).setPostCode("456").builder());
        customers.put("harry.lang@192.com",new CustomerBuilder().setEmailAddress("harry.lang@192.com").setForename("Harry").setSurname("Lang").setCustomerType(CustomerType.NON_PAYING).setPostCode("789").builder());

        return customers
    }


}
