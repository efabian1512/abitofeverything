package com.naifer.wigsshop.wigsshopping.users;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naifer.wigsshop.wigsshopping.productcategories.ProductCategory;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
public class UserResource {
	@Autowired
	private UserService userService; 
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("users/getAll")
	public List<UserInfo> getUsers(){
		return userService.getUsers();
	}
	
	@PostMapping("users/register")
	public String addNewUser(@RequestBody AddUserDTO userDTO) throws MessagingException {
		
		return userService.addUser(userDTO);
	}
	
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("users/delete/{id}")
	public String deleteUser(@PathVariable UUID id) {
		return userService.deleteUser(id);
	}
	
	@PutMapping("shop/users/update")
	public EntityModel<UserInfo> updateUser(@Valid @RequestBody UserInfo user) throws IOException {
		
		UserInfo savedUser = userService.updateUser(user);
		
		EntityModel<UserInfo> entityModel = EntityModel.of(savedUser);
		return entityModel;
	}

}
