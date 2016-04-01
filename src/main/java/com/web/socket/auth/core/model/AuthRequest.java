package com.web.socket.auth.core.model;

public class AuthRequest {

	@Override
	public String toString() {
		return "AuthRequest [type=" + type + ", sequence_id=" + sequence_id + ", data=" + data + "]";
	}

	private String type;
	private String sequence_id;

	private AuthRequestData data;

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

	public AuthRequestData getData() {
		return data;
	}

	public void setData(AuthRequestData data) {
		this.data = data;
	}
}
