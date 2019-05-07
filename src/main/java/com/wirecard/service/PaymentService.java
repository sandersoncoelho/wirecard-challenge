package com.wirecard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wirecard.model.Payment;
import com.wirecard.repository.PaymentRepository;
import com.wirecard.service.PaymentService;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	public String createPayment(Payment payment) {
		paymentRepository.save(payment);
		return "123";
	}

	public Payment getPayment(Long id) {
		return paymentRepository.get(id);
	}
}
