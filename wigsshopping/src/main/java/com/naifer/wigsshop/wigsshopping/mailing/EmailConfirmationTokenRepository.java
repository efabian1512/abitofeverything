package com.naifer.wigsshop.wigsshopping.mailing;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailConfirmationTokenRepository extends JpaRepository<EmailConfirmationToken, UUID> {
	Optional<EmailConfirmationToken> findByToken(String token);
	UUID deleteByToken(String token);
}
