package com.naifer.wigsshop.wigsshopping.auth;

import java.util.Date;

import com.naifer.wigsshop.wigsshopping.users.UserDTO;

public class AuthToken {
	
	private String accessToken;
	
	private Date tokenExpirationDate;
	
	private UserDTO user;

	public AuthToken() {
		super();
	}

	public AuthToken(String accessToken, Date tokenExpirationDate, UserDTO user) {
		super();
		this.accessToken = accessToken;
		this.tokenExpirationDate = tokenExpirationDate;
		this.user = user;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getTokenExpirationDate() {
		return tokenExpirationDate;
	}

	public void setTokenExpirationDate(Date tokenExpirationDate) {
		this.tokenExpirationDate = tokenExpirationDate;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "AuthToken [accessToken=" + accessToken + ", tokenExpirationDate=" + tokenExpirationDate + ", user="
				+ user + "]";
	}		
}
