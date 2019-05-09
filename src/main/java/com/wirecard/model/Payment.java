package com.wirecard.model;

public class Payment {
	private Long id;
	private Double amount;
	private Integer type;
	private Card card;
	private Buyer buyer;
	private Integer status;
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
}
