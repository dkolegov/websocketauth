package com.web.socket.auth.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Notification {
	private Map<String, String> errors = new HashMap<String, String>();

	public void addError(String type, String message) {
		errors.put(type, message);
	}
	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	public String getErrorTypes() {
		return Arrays.toString(errors.keySet().toArray());
	}
	
	public String getErrorDescriptions() {
		return Arrays.toString(errors.values().toArray());
	}
}