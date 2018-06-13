package com.hooyu.exercise.customers.domain;

import java.util.stream.Stream;

public enum CustomerType {
	PREMIUM("Premium"), NON_PAYING("Non Paying");
	private String value;

	CustomerType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}


}