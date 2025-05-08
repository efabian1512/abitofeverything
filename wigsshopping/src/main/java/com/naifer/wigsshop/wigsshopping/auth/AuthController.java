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

import com.naifer.wigsshop.wigsshopping.users.UserDTO;
import com.naifer.wigsshop.wigsshopping.users.UserInfo;
import com.naifer.wigsshop.wigsshopping.users.UserService;
import com.naifer.wihsshop.generic.GenericResponse;


@RestController
public class AuthController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
//	@PostMapping("shop/authenticate")
//	public EntityModel<UserDTO> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//	Authentication authentication =	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//	
//	UserInfo user = userService.finByUserName(authRequest.getUsername());
//	
//	UserDTO userDTO = new UserDTO();
//	
//	userDTO.setName(user.getName());
//	userDTO.setId(user.getId());
//	userDTO.setRoles(user.getRoles());
//	userDTO.setToken(jwtService.generateToken(authRequest.getUsername()));
//	userDTO.setEmail(user.getEmail());
//	
//	
//	if(authentication.isAuthenticated()) {
//		userDTO.setToken(jwtService.generateToken(authRequest.getUsername()));
//		EntityModel<UserDTO> entityModel = EntityModel.of(userDTO);
//		return entityModel;
//	} else {
//		throw new UsernameNotFoundException("invalid user request");
//	}
//		
//	}
	
	@PostMapping("shop/authenticate")
	public ResponseEntity<GenericResponse<AuthToken>> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			
		if(!authentication.isAuthenticated())
			throw new UsernameNotFoundException("Invalid user request.");
		
			GenericResponse<AuthToken> resp = new GenericResponse<AuthToken>();
			
			AuthToken tokenInfo = jwtService.generateToken(authRequest.getUsername());
			
			UserInfo user = userService.getUser(authRequest.getUsername());
			
			UserDTO userDTO = new UserDTO();

			userDTO.setName(user.getName());
			userDTO.setId(user.getId());
			userDTO.setRoles(user.getRoles());
			userDTO.setEmail(user.getEmail());
			userDTO.setAccountVerified(user.isAccountVerified());
			
			tokenInfo.setUser(userDTO);
			//setRefreshToken(authRequest, tokenInfo, userInfo);
			
			resp.setData(tokenInfo);
			resp.setSuccess(true);
			
			return ResponseEntity.ok().body(resp);
	}
	
	@GetMapping("confirm-email")
	public ResponseEntity<?> confirmEmail(@RequestParam("token") String token) {
		if(userService.verifyUser(token)) {
			return ResponseEntity.ok("¡Tu cuenta ha siso verificada correctamente!");
		} else {
			return ResponseEntity.ok("El link expiro o ya esta cuenta ha sido veriricada previamente.");
		}
	}
	
	@PostMapping("shop/logout")
	public ResponseEntity<GenericResponse<String>> logout(@RequestParam("accessToken") String accessToken) {
		String message = jwtService.removeAccessToken(accessToken);
		
		GenericResponse<String> genericResponse = new GenericResponse<String>();
		
		genericResponse.setData(message);
		genericResponse.setSuccess(true);
		
		return ResponseEntity.ok().body(genericResponse);
	}
		
}
