package com.web.socket.auth.core.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class AuthResponseData {

	private String api_token;

	private String api_token_expiration_date;

	private String error_description;
	
	private String error_code;

	public AuthResponseData(String api_token, LocalDateTime api_token_expiration_date) {
		this.api_token = api_token;
		// TODO convert to client date
		this.api_token_expiration_date = api_token_expiration_date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	@Override
	public String toString() {
		return "AuthResponseData [api_token=" + api_token + ", api_token_expiration_date=" + api_token_expiration_date
				+ ", error_description=" + error_description + ", error_code=" + error_code + "]";
	}

	public AuthResponseData(String error_description, String error_code) {
		this.error_description = error_description;
		this.error_code = error_code;
	}

	public String getApi_token() {
		return api_token;
	}
	public void setApi_token(String api_token) {
		this.api_token = api_token;
	}
	public String getApi_token_expiration_date() {
		return api_token_expiration_date;
	}
	public void setApi_token_expiration_date(LocalDateTime api_token_expiration_date) {
		this.api_token_expiration_date = api_token_expiration_date.format(DateTimeFormatter.ISO_INSTANT);
	}

	public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
}
