package com.wirecard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wirecard.enums.PaymentCardStatus;
import com.wirecard.enums.PaymentStatus;
import com.wirecard.enums.PaymentType;
import com.wirecard.model.Buyer;
import com.wirecard.model.Card;
import com.wirecard.model.Payment;
import com.wirecard.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	/**
	 * Persist a payment requested according if its type: BOLETO or CREDIT CARD 
	 * @param payment
	 * @return A boleto number BOLETO or 'succes' or 'fail' for CREDIT CARD
	 */
	public String createPayment(final Payment payment) {
		paymentRepository.save(payment);
		
		if (PaymentType.BOLETO.getValue().equals(payment.getType())) {
			return processBoleto(payment);
		} else if (PaymentType.CREDIT_CARD.getValue().equals(payment.getType())) {
			return processCreditCard(payment);
		} else {
			return null;
		}
	}
	
	/**
	 * Process boleto payment.
	 * @param payment
	 * @return Just a mocking number;
	 */
	private String processBoleto(final Payment payment) {
		if (this.validatePayment(payment) && this.validateBuyer(payment.getBuyer())) {
			payment.setStatus(PaymentStatus.PENDING.getValue());
			return "1234567890"; //boleto number mocked
		} else {
			payment.setStatus(PaymentStatus.DENIED.getValue());
			return null;
		}
	}
	
	/**
	 * Process credit card payment, validating before its datas
	 * @param payment
	 * @return 'success' or 'fail' according its payment process
	 */
	private String processCreditCard(final Payment payment) {
		if (this.validateBuyer(payment.getBuyer()) && validateCard(payment.getCard())) {
			payment.setStatus(PaymentStatus.PAYED.getValue());
			return PaymentCardStatus.SUCCESS.getDescription();
	    } else {
	    	payment.setStatus(PaymentStatus.DENIED.getValue());
	    	return PaymentCardStatus.FAIL.getDescription();
	    }
	}
	
	/**
	 * Validate payment data. Simulation of validation
	 * @param payment
	 * @return true if valid false otherwise
	 */
	private boolean validatePayment(final Payment payment) {
		return payment != null && payment.getAmount() != null && payment.getType() != null;
	}
	
	/**
	 * Validate buyer data. Simulation of validation
	 * @param buyer
	 * @return true if valid false otherwise
	 */
	private boolean validateBuyer(final Buyer buyer) {
		return buyer != null && buyer.getId() != null && buyer.getCpf() != null
				&& buyer.getName() != null;
	}
	
	/**
	 * Validate card data. Simulation of validation
	 * @param buyer
	 * @return true if valid false otherwise
	 */
	private boolean validateCard(final Card card) {
		return card != null && card.getId() != null && card.getExpirationDate() != null
				&& card.getNumber() != null && card.getCvv() != null && card.getHolderName() != null;
	}

	/**
	 * Retrieve a payment
	 * @param id
	 * @return payment
	 */
	public Payment getPayment(final Long id) {
		return paymentRepository.get(id);
	}
	
	/**
	 * Retrieve a payment status
	 * @param id
	 * @return payment
	 */
	public Integer getPaymentStatus(final Long id) {
		return paymentRepository.getPaymentStatus(id);
	}
}
