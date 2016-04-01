package com.web.socket.auth.core.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
public class DBUser {
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password_hash=****************]";
	}
	@Id @GeneratedValue
	private Long userId;
	@NotEmpty
	private String email;
	@NotEmpty
	private String password_hash;

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return password_hash;
	}
}

