package com.naifer.wigsshop.wigsshopping.auth;

public class LogoutAccessToken {
	
	private String accessToken;

	public LogoutAccessToken() {
		super();
	}

	public LogoutAccessToken(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "LogoutAccessToken [accessToken=" + accessToken + "]";
	}	
}
