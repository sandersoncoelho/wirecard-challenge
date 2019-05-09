package com.wirecard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wirecard.enums.PaymentCardStatus;
import com.wirecard.enums.PaymentType;
import com.wirecard.model.Payment;
import com.wirecard.repository.PaymentRepository;
import com.wirecard.service.PaymentService;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	public String createPayment(Payment payment) {
		paymentRepository.save(payment);
		
		if (PaymentType.BOLETO.getValue().equals(payment.getType())) {
			return "1234567890"; //boleto number
		} else if (PaymentType.CREDIT_CARD.getValue().equals(payment.getType())) {
			return PaymentCardStatus.SUCCESS.getStatus();
		} else {
			return null;
		}
	}

	public Payment getPayment(Long id) {
		return paymentRepository.get(id);
	}
}
