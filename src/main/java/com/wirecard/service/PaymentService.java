package com.wirecard.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wirecard.enums.PaymentCardStatus;
import com.wirecard.enums.PaymentStatus;
import com.wirecard.enums.PaymentType;
import com.wirecard.model.Buyer;
import com.wirecard.model.Card;
import com.wirecard.model.Payment;
import com.wirecard.repository.BuyerRepository;
import com.wirecard.repository.CardRepository;
import com.wirecard.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	/**
	 * Get all payments.
	 * @return all payments.
	 */
	public Iterable<Payment> getAll() {
		return paymentRepository.findAll();
	}

	/**
	 * Persist a payment requested according if its type: BOLETO or CREDIT CARD.
	 * @param payment
	 * @return A boleto number BOLETO or 'succes' or 'fail' for CREDIT CARD.
	 */
	public String createPayment(final Payment payment) {
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
	 * @return Just a mocking number.
	 */
	private String processBoleto(final Payment payment) {
		if (this.validatePayment(payment) && this.validateBuyer(payment.getBuyer())) {
			payment.setStatus(PaymentStatus.PENDING.getValue());
			payment.setBoletoNumber(new Date().getTime() + ""); //boleto number mocked
			
			Buyer buyer = buyerRepository.save(payment.getBuyer());
			payment.setBuyer(buyer);
		} else {
			payment.setStatus(PaymentStatus.DENIED.getValue());
		}
		
		paymentRepository.save(payment);
		return payment.getBoletoNumber();
	}
	
	/**
	 * Process credit card payment, validating before its datas.
	 * @param payment
	 * @return 'success' or 'fail' according its payment process.
	 */
	private String processCreditCard(final Payment payment) {
		String creditCardStatus = null;
		
		if (this.validateBuyer(payment.getBuyer()) && validateCard(payment.getCard())) {
			payment.setStatus(PaymentStatus.PAYED.getValue());
			creditCardStatus = PaymentCardStatus.SUCCESS.getDescription();
			
			Buyer buyer = buyerRepository.save(payment.getBuyer());
			payment.setBuyer(buyer);
			
			Card card = cardRepository.save(payment.getCard());
			payment.setCard(card);
	    } else {
	    	payment.setStatus(PaymentStatus.DENIED.getValue());
	    	creditCardStatus = PaymentCardStatus.FAIL.getDescription();
	    }
		
		paymentRepository.save(payment);
		return creditCardStatus;
	}
	
	/**
	 * Validate payment data. Simulation of validation.
	 * @param payment
	 * @return true if valid false otherwise.
	 */
	private boolean validatePayment(final Payment payment) {
		return payment != null && payment.getAmount() != null && payment.getType() != null;
	}
	
	/**
	 * Validate buyer data. Simulation of validation.
	 * @param buyer
	 * @return true if valid false otherwise.
	 */
	private boolean validateBuyer(final Buyer buyer) {
		return buyer != null && buyer.getCpf() != null
				&& buyer.getName() != null;
	}
	
	/**
	 * Validate card data. Simulation of validation.
	 * @param buyer
	 * @return true if valid false otherwise.
	 */
	private boolean validateCard(final Card card) {
		return card != null && card.getExpirationDate() != null && card.getNumber() != null
				&& card.getCvv() != null && card.getHolderName() != null;
	}

	/**
	 * Retrieve a payment.
	 * @param id
	 * @return payment.
	 */
	public Payment getPayment(final String id) {
		Optional<Payment> optional = paymentRepository.findById(id);
		return optional.isPresent()? optional.get() : null;
	}
	
	/**
	 * Retrieve a payment status.
	 * @param id
	 * @return payment.
	 */
	public Integer getPaymentStatus(final String id) {
		Optional<Payment> optional = paymentRepository.findById(id);
		return optional.isPresent()? optional.get().getStatus() : null;
	}
}
