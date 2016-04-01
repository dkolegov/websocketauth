package com.web.socket.auth.core.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class AuthResponse {
	@Override
	public String toString() {
		return "AuthResponse [type=" + type + ", sequence_id=" + sequence_id + ", data=" + data + "]";
	}

	private String type;

	private String sequence_id;

	private AuthResponseData data;


	public AuthResponse() {}
	
	public AuthResponse(String type, String errorType, String errorDescription) {
		this.type = type;
		this.data = new AuthResponseData(errorDescription, errorType);
	}

	public AuthResponse(String type, String sequence_id, String apiToken, LocalDateTime date) {
		this.type = type;
		this.sequence_id = sequence_id;
		this.data = new AuthResponseData(apiToken, date);
	}
	
	public AuthResponseData getData() {
		return data;
	}
	public void setData(AuthResponseData data) {
		this.data = data;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getSequence_id() {
		return sequence_id;
	}

	public void setSequence_id(String sequence_id) {
		this.sequence_id = sequence_id;
	}
}
