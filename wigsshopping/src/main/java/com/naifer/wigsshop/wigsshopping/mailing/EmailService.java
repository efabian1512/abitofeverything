package com.naifer.wigsshop.wigsshopping.mailing;

import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;

@Component
public interface EmailService {
	
	void sendConfirmationEmail(EmailConfirmationToken emailConfirmationToken, String confirmationURL) throws MessagingException;
}
