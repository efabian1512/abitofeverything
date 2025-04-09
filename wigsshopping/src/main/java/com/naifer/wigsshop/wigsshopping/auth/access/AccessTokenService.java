package com.naifer.wigsshop.wigsshopping.auth.access;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenService {
	
	@Autowired
	private AccessTokenRepository accessTokenRepository;
	
	public void saveAccessToken(AccessToken token) {
		accessTokenRepository.save(token);
	}
	
	public Optional<AccessToken> getAccessToken(String token) {
		
		return accessTokenRepository.findByToken(token);
		
	}
	
	public Optional<AccessToken> getAccessTokenByUserName(String username) {
		
		return accessTokenRepository.findByUsername(username);
		
	}
	
	public void deleteAccessToken(AccessToken token) {
		accessTokenRepository.delete(token);
	}
	
}
