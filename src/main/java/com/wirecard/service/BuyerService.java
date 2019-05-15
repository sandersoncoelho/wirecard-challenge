package com.wirecard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wirecard.model.Buyer;
import com.wirecard.repository.BuyerRepository;

@Service
public class BuyerService {
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	/**
	 * Get all buyers.
	 * @return all buyers.
	 */
	public Iterable<Buyer> getAll() {
		return buyerRepository.findAll();
	}

	/**
	 * Persist a buyer.
	 * @param buyer
	 * @return A persisted buyer.
	 */
	public Buyer createBuyer(final Buyer buyer) {
		return buyerRepository.save(buyer);
	}
}
