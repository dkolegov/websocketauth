package com.web.socket.auth.core.model;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User {

	@Override
	public String toString() {
		return "User [email=" + email + ", token=" + token + "]";
	}

	private String email;
	
	private String token;

    @PostConstruct
    public void init() {
        // Invoked after dependencies injected
    }

    @PreDestroy
    public void destroy() {
        // Invoked when the WebSocket session ends
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}