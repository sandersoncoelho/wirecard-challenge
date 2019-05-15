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
	 * Get all payments.
	 * @return all payments.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Payment> getAll() {
		return paymentService.getAll();
	}

	/**
	 * Endpoint to create payments.
	 * @param payment
	 * @return The number of boleto or 'sucess' or 'fail' for card request payment.
	 */
	@RequestMapping(method = RequestMethod.POST)
    public String createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }
	
	/**
	 * Endpoint to return all the information about the payment.
	 * @param id
	 * @return Payment.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Payment getPayment(@PathVariable("id") String id) {
    	return paymentService.getPayment(id);
    }
	
	/**
	 * Endpoint to return the payment status.
	 * @param id
	 * @return Payments status.
	 */
	@RequestMapping(value = "/{id}/status", method = RequestMethod.GET)
    public Integer getPaymentStatus(@PathVariable("id") String id) {
    	return paymentService.getPaymentStatus(id);
    }
}
