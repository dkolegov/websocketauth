package com.web.socket.auth.core.model;

import java.util.Arrays;

public class AuthRequestData {

	@Override
	public String toString() {
		return "AuthRequestData [email=" + email + ", password=" + Arrays.toString(password) + "]";
	}

	private String email;

	private char[] password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}
}
