package com.wirecard.repository;

import org.springframework.stereotype.Repository;

import com.wirecard.model.Buyer;

@Repository
public class BuyerRepository {
	
	public Buyer save(Buyer buyer) {
		buyer.setId(12L);
		return buyer;
	}
}
