package com.web.socket.auth.core.db;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class DBToken {
	
	@Override
	public String toString() {
		return "DBToken [tokenId=" + tokenId + ", token=" + token + ", user=" + user + ", time=" + time + "]";
	}
	@Id @GeneratedValue
	private Long tokenId;

	@NotEmpty
	private String token;

	@OneToOne(targetEntity=DBUser.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private DBUser user;

	private LocalDateTime time;

	public DBToken(String token, DBUser user) {
		this.token = token;
		this.user = user;
		time = LocalDateTime.now();
	}

	public DBToken() {
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
}
