package com.naifer.wigsshop.wigsshopping.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naifer.wigsshop.wigsshopping.users.UserService;


@RestController
public class AuthController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("shop/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
	Authentication authentication =	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	
	if(authentication.isAuthenticated()) {
		return jwtService.generateToken(authRequest.getUsername());
	} else {
		throw new UsernameNotFoundException("invalid user request");
	}
		
	}
	
	@GetMapping("confirm-email")
	public ResponseEntity<?> confirmEmail(@RequestParam("token") String token) {
		if(userService.verifyUser(token)) {
			return ResponseEntity.ok("Your email has been successfully verified");
		} else {
			return ResponseEntity.ok("Link expired or token already  verified");
		}
	}
}
