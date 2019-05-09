package com.wirecard.enums;

public enum PaymentType {
	CREDIT_CARD(1), BOLETO(2);
	private Integer value;
	
	private PaymentType(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
}
