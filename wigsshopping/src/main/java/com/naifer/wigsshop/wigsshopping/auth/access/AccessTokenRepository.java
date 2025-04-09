package com.naifer.wigsshop.wigsshopping.auth.access;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AccessTokenRepository extends JpaRepository<AccessToken, UUID> {
	
	Optional<AccessToken> findByToken(String token);
	
	Optional<AccessToken>findByUsername(String username);

}
