package com.naifer.wigsshop.wigsshopping.mailing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender sender;
	
	@Override
	public void sendConfirmationEmail(EmailConfirmationToken emailConfirmationToken, String confirmationURL) throws MessagingException {
		//MIME - HTML message
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(emailConfirmationToken.getUser().getEmail());
		helper.setSubject("Confirm your E-Mail - AbitOfEverything Shop Registration");
		helper.setText("<html>" +
					"<body>" +
					"<h2> Dear "+ emailConfirmationToken.getUser().getName() + ".</h2>"
					+ "<br/> We're excited to have you get started. "
					+ "Please click on below link to confirm your account."
					+ "<br/>" + generateConfirmationLink(emailConfirmationToken.getToken(), confirmationURL)+""+
					"<br/> Regards, <br/>"+
					"A bit of Everything Registration team"+
					"</body>"+
					"<html>"
					,true);
		sender.send(message);
	}
	
	private String generateConfirmationLink(String token, String confirmationURL) {
		return "<a href="+confirmationURL+"?token="+token+">Confirm Email</a>";
		//return "<a href=http://localhost:9090/confirm-email?token="+token+">Confirm Email</a>";
	}

}
