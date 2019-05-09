package com.wirecard.enums;

public enum PaymentCardStatus {
	SUCCESS(1, "success"),
	FAIL(2, "fail");
	
	private Integer value;
	private String description;
	
	private PaymentCardStatus(Integer value, String description) {
		this.value = value;
		this.description = description;
	}

	public Integer getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
}
