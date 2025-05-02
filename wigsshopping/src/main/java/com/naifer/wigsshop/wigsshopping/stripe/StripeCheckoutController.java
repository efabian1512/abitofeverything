package com.naifer.wigsshop.wigsshopping.stripe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping("/shop")
public class StripeCheckoutController {
	
	@Autowired
	private StripeService stripeService;

	@PostMapping("card/checkout")
	public ResponseEntity<StripeResponse> checkout(@RequestBody StripeCheckoutRequest request ){
		
		StripeResponse stripeResponse =	stripeService.checkoutProducts(request);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(stripeResponse);
	}
	
	@PostMapping("/create/checkout-session")
	public String paymentIntent(@RequestParam("amount") Long amount, @RequestParam("currency") String currency ) throws StripeException{
		
		return	stripeService.createAPaymentIntent(Long.valueOf(amount), currency);
		
	}
}
