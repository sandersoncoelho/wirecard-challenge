package com.wirecard.model;

import com.wirecard.enums.PaymentType;

public class Payment {
	private Long id;
	private Double amount;
	private PaymentType type;
	private Card card;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public PaymentType getType() {
		return type;
	}
	public void setType(PaymentType type) {
		this.type = type;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
}
