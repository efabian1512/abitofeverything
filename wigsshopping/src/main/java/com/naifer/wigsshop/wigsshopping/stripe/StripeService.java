package com.naifer.wigsshop.wigsshopping.stripe;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;

@Component
public class StripeService {
	
	@Value("${stripe.secretKey}")
	private String secretKey;
	
	public StripeResponse checkoutProducts(StripeCheckoutRequest productRequest) {
		Stripe.apiKey = secretKey;
		
		SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData.builder().setName(productRequest.getName()).build();
		SessionCreateParams.LineItem.PriceData priceData =	SessionCreateParams.LineItem.PriceData.builder()
												.setCurrency(productRequest.getCurrency() == null ? "USD" : productRequest.getCurrency())
												.setUnitAmount(productRequest.getAmount())
												.setProductData(productData)
												.build();
		
		SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
										.setQuantity(productRequest.getQuantity())
										.setPriceData(priceData)
										.build();
		
		SessionCreateParams params = SessionCreateParams.builder()
							.setMode(SessionCreateParams.Mode.PAYMENT)
							.setSuccessUrl(productRequest.getSuccessUrl())
							.setCancelUrl(productRequest.getCancelUrl())
							.addLineItem(lineItem)
							.build();
		
		Session session = null;
		
		try {
			session = Session.create(params);
		} catch(StripeException ex) {
			System.out.println(ex.getMessage());
		}
		
		StripeResponse response = new StripeResponse();
		response.setStatus("SUCCESS");
		response.setMessage("Payment session created");
		response.setSessionId(session.getId());
		response.setSessionUrl(session.getUrl());
		
		return response;
		
	}
	
	public String createAPaymentIntent(Long amount, String currency) throws StripeException {
		Stripe.apiKey = secretKey;
		PaymentIntentCreateParams params =
				  PaymentIntentCreateParams.builder()
				    .setAmount(amount)
				    .setCurrency(currency != null ? currency : "usd")
				    .addAllPaymentMethodType(Arrays.asList("card"))
//				    .setAutomaticPaymentMethods(
//				      PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
//				        .setEnabled(false)
//				        .build()
//				    )
				    .build();
				PaymentIntent paymentIntent = PaymentIntent.create(params);
				
				return paymentIntent.getClientSecret();
	}
}
