package com.wirecard.enums;

public enum PaymentCardStatus {
	SUCCESS("success"),
	FAIL("fail");
	
	private String status;
	
	private PaymentCardStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
