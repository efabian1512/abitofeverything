package com.naifer.wigsshop.wigsshopping.paypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@RestController()
@RequestMapping("/paypal")
public class PayPalController {
	
	@Autowired
	private PaypalService paypalService;
	
	@GetMapping("/") 
	public String home() {
		return "payment";
	}
	
	@PostMapping("/pay")
	public String makePayment(
			@RequestParam("amount") double amount, 
			@RequestParam("description") String description, 
			@RequestParam("cancelUrl") String cancelUrl, 
			@RequestParam("successUrl") String successUrl
			) throws PayPalRESTException {
				Payment payment = paypalService.createPayment(
				Double.valueOf(amount), 
				"USD", 
				"paypal", 
				"sale", 
				description, 
				cancelUrl, 
				successUrl);
		
		for(Links links: payment.getLinks()) {
			if(links.getRel().equals("approval_url")) {
				return "Redirect to: "+links.getHref();
			}
		}
		
		return "Error processing the payment";
	}
	
	@GetMapping("/success")
	public String paymentSuccess(
			@RequestParam("paymentId") String paymentId, 
			@RequestParam("payerId") String payerId) throws PayPalRESTException {
		
		Payment payment = paypalService.execute(paymentId, payerId);
		
		if(payment.getState().equals("approved")) {
			return "payment is successfully done";
		}
		
		return "payment failed";
	}
	
	@GetMapping("/cancel") 
	public String cancel() {
		return "payment cancelled";
	}
	
	@GetMapping("/error") 
	public String paymentError() {
		return "payment cancelled";
	}

}
