package com.naifer.wigsshop.wigsshopping.users;

public class AddUserDTO {
	
	private UserInfo userInfo;
	private String confirmationUrl;
	
	public AddUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddUserDTO(UserInfo userInfo, String confirmationUrl) {
		super();
		this.userInfo = userInfo;
		this.confirmationUrl = confirmationUrl;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getConfirmationUrl() {
		return confirmationUrl;
	}

	public void setConfirmationUrl(String confirmationUrl) {
		this.confirmationUrl = confirmationUrl;
	}

	@Override
	public String toString() {
		return "AddUserDTO [userInfo=" + userInfo + ", confirmationUrl=" + confirmationUrl + "]";
	}
}
