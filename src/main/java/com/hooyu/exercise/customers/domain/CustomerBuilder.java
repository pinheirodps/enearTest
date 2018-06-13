package com.hooyu.exercise.customers.domain;

public class CustomerBuilder {
    private String forename;
    private String surname;
    private String emailAddress;
    private CustomerType customerType;
    private String postCode;

    public CustomerBuilder(String email, String forename, String surname, CustomerType type, String postCode) {
        this.forename = forename;
        this.surname = surname;
        this.emailAddress = email;
        this.customerType = type;
        this.postCode = postCode;
    }

    public CustomerBuilder() {
    }

    public CustomerBuilder setForename(String forename) {
        this.forename = forename;
        return this;
    }

    public CustomerBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public CustomerBuilder setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public CustomerBuilder setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
        return this;
    }

    public CustomerBuilder setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public Customer builder() {
        return new Customer(this);
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public String getPostCode() {
        return postCode;
    }
}
