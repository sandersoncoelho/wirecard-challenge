package com.wirecard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wirecard.repository.CardRepository;

@Service
public class CardService {
	
	@Autowired
	private CardRepository cardRepository;

	/**
	 * Check card status by its number
	 * @param card number
	 * @return true if is valid false otherwise
	 */
	public Boolean isValidCard(String number) {
		return cardRepository.isValidCard(number);
	}
	
	/**
	 * Get card issuer by its number
	 * @param card number
	 * @return Card issuer
	 */
	public String getCardIssuer(String number) {
		return cardRepository.getCardIssuer(number);
	}
}
