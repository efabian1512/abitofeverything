package com.naifer.wigsshop.wigsshopping.auth.access;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="access_token")
public class AccessToken {
	
	@Id
	@GeneratedValue(strategy= GenerationType.UUID)
	private UUID id;
	
	private String token;
	
	private Boolean revoked;
	
	private String username;

	public AccessToken() {
		super();
	}

	public AccessToken(UUID id, String token, Boolean revoked, String username) {
		super();
		this.id = id;
		this.token = token;
		this.revoked = revoked;
		this.username = username;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(Boolean revoked) {
		this.revoked = revoked;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "AccessToken [id=" + id + ", token=" + token + ", revoked=" + revoked + ", username=" + username + "]";
	}
}
