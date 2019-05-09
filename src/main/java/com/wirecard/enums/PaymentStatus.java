package com.wirecard.enums;

public enum PaymentStatus {
	PENDING(1),
	PAYED(2),
	DENIED(3);
	
	private Integer value;
	
	private PaymentStatus(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
