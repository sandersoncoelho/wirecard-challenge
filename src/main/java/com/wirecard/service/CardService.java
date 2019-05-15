package com.wirecard.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wirecard.model.Card;
import com.wirecard.repository.CardRepository;

@Service
public class CardService {
	
	@Autowired
	private CardRepository cardRepository;
	
	/**
	 * Get all cards.
	 * @return all cards.
	 */
	public Iterable<Card> getAll() {
		return cardRepository.findAll();
	}

	/**
	 * Check card status by its number.
	 * @param card number.
	 * @return true if is valid false otherwise.
	 */
	public Boolean isValidCard(String number) {
		Card card = cardRepository.findByNumber(number);
		return card != null && card.getExpirationDate().before(new Date())
				&& card.getHolderName() != null;
	}
	
	/**
	 * Get card issuer by its number.
	 * @param card number.
	 * @return Card issuer.
	 */
	public String getCardIssuer(String number) {
		Card card = cardRepository.findByNumber(number);
		return card != null? card.getHolderName() : null;
	}
}
