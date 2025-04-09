package com.naifer.wigsshop.wigsshopping.users;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.naifer.wigsshop.wigsshopping.mailing.EmailConfirmationToken;
import com.naifer.wigsshop.wigsshopping.mailing.EmailService;
import com.naifer.wigsshop.wigsshopping.mailing.EmailConfirmationTokenRepository;

import jakarta.mail.MessagingException;

@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailConfirmationTokenRepository emailTokenRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	
	public List<UserInfo> getUsers(){
	 List<UserInfo> users =	userRepository.findAll();
	 
	 if(users.isEmpty())
		 return List.of();
	 
	 return users;
	}
	
	public String addUser(UserInfo userInfo) throws MessagingException {
		
		Optional<UserInfo> savedUser = userRepository.findByEmail(userInfo.getEmail());
		
		String message = "User successfully created";
		if(savedUser.isPresent()) {
			message = "Registration was unssucessful. User " + userInfo.getEmail()+ " already exists.";
			return message;
		//throw new RuntimeException(message);
		}
		
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userRepository.save(userInfo);
		
		this.sendRegistrationConfirmationEmail(userInfo);
		return message;
	}
	
	public String deleteUser(UUID userId) {
		Optional<UserInfo> savedUser = userRepository.findById(userId);
		
		if(savedUser.isEmpty())
			throw new UsernameNotFoundException("User with id" + userId + "was not found");
		
		userRepository.deleteById(userId);
		
		return "User successfully removed";
		
	}
	
	public void sendRegistrationConfirmationEmail(UserInfo user) throws MessagingException {
		
		String tokenValue = new String(Base64.getEncoder().encodeToString(KeyGenerators.secureRandom(15).generateKey()));
		EmailConfirmationToken emailConfirmationToken = new EmailConfirmationToken();
		emailConfirmationToken.setToken(tokenValue);
		emailConfirmationToken.setTimeStamp(LocalDateTime.now());
		emailConfirmationToken.setUser(user);
		
		emailTokenRepository.save(emailConfirmationToken);
		
		emailService.sendConfirmationEmail(emailConfirmationToken);
	}
	
	public boolean verifyUser(String token) throws RuntimeException {
		Optional<EmailConfirmationToken> emailConfirmationTokenOptional = emailTokenRepository.findByToken(token);
		
		
		if(emailConfirmationTokenOptional.isEmpty() || !token.equals(emailConfirmationTokenOptional.get().getToken())) {
			throw new RuntimeException("Token is not valid");
		}
		
		EmailConfirmationToken emailConfirmationToken = emailConfirmationTokenOptional.get();
		UserInfo user = emailConfirmationToken.getUser();
		
		if(Objects.isNull(user)) {
			return false;
		}
		
		user.setAccountVerified(true);
		userRepository.save(user);
		emailTokenRepository.delete(emailConfirmationToken);
		return true;
	}
	
	public UserInfo getUser(String username) {
		Optional<UserInfo> savedUser = userRepository.findByEmail(username);
		
		if(savedUser.isEmpty())
			throw new RuntimeException("User not found");
		
		return savedUser.get();
	}
	
	public UserInfo updateUser(UserInfo user) {
		Optional<UserInfo> savedUser = userRepository.findById(user.getId());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		if(savedUser.isEmpty())
			throw new RuntimeException("User not found");
		
		return userRepository.save(user);
	}
}
