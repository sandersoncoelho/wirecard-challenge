package com.wirecard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wirecard.model.Payment;
import com.wirecard.service.PaymentService;


@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;

	/**
	 * Endpoint to create payments
	 * @param payment
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
    public String createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }
	
	/**
	 * Endpoint to return all the information about the payment
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public Payment getPayment(@PathVariable("id") Long id) {
    	return paymentService.getPayment(id);
    }
}
