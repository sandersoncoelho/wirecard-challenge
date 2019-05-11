package com.wirecard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wirecard.model.Buyer;
import com.wirecard.service.BuyerService;


@RestController
@RequestMapping("/buyers")
public class BuyerController {
	
	@Autowired
	private BuyerService buyerService;
	
	/**
	 * Get all buyers
	 * @return all buyers
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Buyer> getAll() {
		return buyerService.getAll();
	}

	/**
	 * Endpoint to create buyers
	 * @param buyer
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
    public Buyer createBuyer(@RequestBody Buyer buyer) {
        return buyerService.createBuyer(buyer);
    }
}
