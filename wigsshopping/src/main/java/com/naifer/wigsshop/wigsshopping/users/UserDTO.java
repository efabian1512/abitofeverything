package com.naifer.wigsshop.wigsshopping.users;

import java.util.UUID;

public class UserDTO {

	public UUID id;
	
	public String name;
	
	public String email;
	
	public String roles;
	
	private boolean accountVerified;
	

	public UserDTO() {
		super();
	}


	public UserDTO(UUID id, String name, String email, String roles, boolean accountVerified) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = roles;
		this.accountVerified = accountVerified;
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}


	public boolean isAccountVerified() {
		return accountVerified;
	}


	public void setAccountVerified(boolean accountVerified) {
		this.accountVerified = accountVerified;
	}


	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", email=" + email + ", roles=" + roles + ", accountVerified="
				+ accountVerified + "]";
	}
}
