package com.hooyu.exercise.customers.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotEmpty;

public class Customer {


	private String forename;
	@NotEmpty(message = "Surname is required")
	private String surname;
	private String emailAddress;
	private CustomerType customerType;
	@NotEmpty(message = "postCode is required")
	private String postCode;

	public Customer(CustomerBuilder customerBuilder) {
		this.forename = customerBuilder.getForename();
		this.surname = customerBuilder.getSurname();
		this.emailAddress = customerBuilder.getEmailAddress();
		this.customerType = customerBuilder.getCustomerType();
		this.postCode = customerBuilder.getPostCode();
	}

	public Customer() {
	}

	public String getForename() {
		return forename;
	}
	
	public void setForename(String forename) {
		this.forename = forename;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public CustomerType getCustomType() {
		return customerType;
	}

	public void setCustomType(CustomerType customType) {
		this.customerType = customType;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public CustomerType getCustomerType() {
		return customerType;
	}
}