package com.wirecard.repository;

import org.springframework.stereotype.Repository;

@Repository
public class CardRepository {
	
	public Boolean isValidCard(String number) {
		return number != null && number.matches("\\d+");
	}
	
	public String getCardIssuer(String number) {
		return "MASTER";
	}
}
