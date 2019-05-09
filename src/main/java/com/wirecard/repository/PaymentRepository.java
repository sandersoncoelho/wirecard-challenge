package com.wirecard.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wirecard.enums.PaymentType;
import com.wirecard.model.Payment;

@Repository
public class PaymentRepository {

	private static List<Payment> mockList = new ArrayList<>();
	
	public List<Payment> list() {
		return mockList;
	}
	
	public Payment get(Long id) {
		return mockPayment(id, PaymentType.BOLETO.getValue(), 12.12);
	}
	
	public Payment save(Payment payment) {
		return payment;
	}
	
	public void delete(Long id) {
		
	}
	
	private Payment mockPayment(Long id, Integer paymentType, Double amount) {
		Payment payment = new Payment();
		payment.setId(id);
		payment.setType(paymentType);
		payment.setAmount(amount);
		return payment;
	}
}
