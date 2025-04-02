package com.naifer.wigsshop.wigsshopping.users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public List<UserInfo> getUsers(){
	 List<UserInfo> users =	userRepository.findAll();
	 
	 if(users.isEmpty())
		 return List.of();
	 
	 return users;
	}
	
	public String addUser(UserInfo userInfo) {
		
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userRepository.save(userInfo);
		
		return "User successfully saved";
	}
	
	public String deleteUser(UUID userId) {
		Optional<UserInfo> savedUser = userRepository.findById(userId);
		
		if(savedUser.isEmpty())
			throw new UsernameNotFoundException("User with id" + userId + "was not found");
		
		userRepository.deleteById(userId);
		
		return "User successfully removed";
		
	}
}
