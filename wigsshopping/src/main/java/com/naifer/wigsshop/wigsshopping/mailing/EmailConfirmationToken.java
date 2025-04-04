package com.naifer.wigsshop.wigsshopping.mailing;

import java.time.LocalDateTime;
import java.util.UUID;

import com.naifer.wigsshop.wigsshopping.users.UserInfo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name="email_tokens")
public class EmailConfirmationToken {

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	private String token;
	@Column(name="time_stamp")
	private LocalDateTime timeStamp;
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="fk_email_token_id", referencedColumnName = "id" )
	private UserInfo user;
	
	public EmailConfirmationToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailConfirmationToken(UUID id, String token, LocalDateTime timeStamp, UserInfo user) {
		super();
		this.id = id;
		this.token = token;
		this.timeStamp = timeStamp;
		this.user = user;
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

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "EmailConfirmationToken [id=" + id + ", token=" + token + ", timeStamp=" + timeStamp + ", user=" + user
				+ "]";
	}
}
