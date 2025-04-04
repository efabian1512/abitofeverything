package com.naifer.wigsshop.wigsshopping.mailing;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailConfirmationTokenResource {

	@Autowired
	private EmailConfirmationTokenService emailTokenConfirmationService;
	
	@GetMapping("confirmation-token/getAll")
	public List<EmailConfirmationToken> getTokens(){
		return emailTokenConfirmationService.getTokens();
	}
	
	@DeleteMapping("confirmation-token/delete/{id}")
	public String deleteToken(@PathVariable UUID id) {
		return emailTokenConfirmationService.deleteToken(id);
	}
}
