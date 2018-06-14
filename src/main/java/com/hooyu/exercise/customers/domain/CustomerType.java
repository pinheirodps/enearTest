package com.hooyu.exercise.customers.domain;

public enum CustomerType {
	PREMIUM("Premium"), NON_PAYING("Non Paying");
	private String message;

	CustomerType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}


}