package com.wirecard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wirecard.model.Card;
import com.wirecard.service.CardService;


@RestController
@RequestMapping("/cards")
public class CardController {
	
	@Autowired
	private CardService cardService;
	
	/**
	 * Get all cards.
	 * @return all cards.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Card> getAll() {
		return cardService.getAll();
	}

	/**
	 * Endpoint to check status of a card, given its number.
	 * @param card
	 * @return true if valid, false otherwise.
	 */
	@RequestMapping(value = "/{number:\\d+}/status", method = RequestMethod.GET)
    public Boolean isValidCard(@PathVariable("number") String number) {
        return cardService.isValidCard(number);
    }
	
	/**
	 * Endpoint to return the card issuer name.
	 * @param id
	 * @return Card issuer name.
	 */
	@RequestMapping(value = "/{number:\\d+}/issuer", method = RequestMethod.GET)
    public String getCardIssuer(@PathVariable("number") String number) {
    	return cardService.getCardIssuer(number);
    }
}
