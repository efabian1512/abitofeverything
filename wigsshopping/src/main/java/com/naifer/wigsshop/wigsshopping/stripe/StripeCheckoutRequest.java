package com.naifer.wigsshop.wigsshopping.stripe;

public class StripeCheckoutRequest {

	private Long amount;
	private Long quantity;
	private String name;
	private String currency;
	private String successUrl;
	private String cancelUrl;
	
	public StripeCheckoutRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StripeCheckoutRequest(Long amount, Long quantity, String name, String currency, String successUrl,
			String cancelUrl) {
		super();
		this.amount = amount;
		this.quantity = quantity;
		this.name = name;
		this.currency = currency;
		this.successUrl = successUrl;
		this.cancelUrl = cancelUrl;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getCancelUrl() {
		return cancelUrl;
	}

	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}

	@Override
	public String toString() {
		return "StripeCheckoutRequest [amount=" + amount + ", quantity=" + quantity + ", name=" + name + ", currency="
				+ currency + ", successUrl=" + successUrl + ", cancelUrl=" + cancelUrl + "]";
	}
}
