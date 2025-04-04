package com.naifer.wigsshop.wigsshopping.users;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="User_Details")
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String email;
	private String password;
	private String roles;
	private boolean accountVerified;

	
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserInfo(UUID id, String name, String email, String password, String roles, boolean accountVerified) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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
		return "UserInfo [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", roles="
				+ roles + ", accountVerified=" + accountVerified + "]";
	}
}
