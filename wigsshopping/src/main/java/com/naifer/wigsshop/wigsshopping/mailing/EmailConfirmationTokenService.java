package com.naifer.wigsshop.wigsshopping.mailing;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailConfirmationTokenService {

	@Autowired
	private EmailConfirmationTokenRepository emailConfirmationRepository;
	
	public List<EmailConfirmationToken> getTokens(){
		List<EmailConfirmationToken> tokens = emailConfirmationRepository.findAll();
		
		if(tokens.isEmpty())
			return List.of();
		
		return tokens;
	}
	
	public String deleteToken(UUID id) {
		
		Optional<EmailConfirmationToken> token = emailConfirmationRepository.findById(id);
		
		if(token.isEmpty())
			throw new RuntimeException("The token with id "+id+ " doesn't exist");
			
		emailConfirmationRepository.deleteById(id);
		
		return "Token successfully removed";
	}
}
