package com.naifer.wigsshop.wigsshopping.users;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
	@Autowired
	private UserService userService; 
	
	@GetMapping("users/getAll")
	public List<UserInfo> getUsers(){
		return userService.getUsers();
	}
	
	@PostMapping("users/register")
	public String addNewUser(@RequestBody UserInfo userInfo) {
		
		return userService.addUser(userInfo);
	}
	
	@DeleteMapping("users/delete/{id}")
	public String deleteUser(@PathVariable UUID id) {
		return userService.deleteUser(id);
	}

}
