package com.wirecard;

import java.util.Date;

import com.wirecard.model.Buyer;
import com.wirecard.model.Card;
import com.wirecard.model.Payment;

public class MockUtils {

	public static Payment mockPayment(Integer paymentType, Double amount) {
		Payment payment = new Payment();
		payment.setType(paymentType);
		payment.setAmount(amount);
		return payment;
	}
	
	public static Buyer mockBuyer(String name, String cpf, String email) {
		Buyer buyer = new Buyer();
		buyer.setName(name);
		buyer.setCpf(cpf);
		buyer.setEmail(email);
		return buyer;
	}
	
	public static Card mockCard(String holderName, String number, Date expirationDate, Integer cvv) {
		Card card = new Card();
		card.setHolderName(holderName);
		card.setNumber(number);
		card.setExpirationDate(expirationDate);
		card.setCvv(cvv);
		return card;
	}
}
