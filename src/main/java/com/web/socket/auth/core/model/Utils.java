package com.web.socket.auth.core.model;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Utils {
	
	private static String solt = "$2a$10$2gBPHiWvGGK2767EYDj2eO";

	public static String getHash(char[] password) {
		return BCrypt.hashpw(String.copyValueOf(password), solt);
	}
	public static String getHash(String password) {
		return BCrypt.hashpw(password, solt);
	}

	public static boolean checkHash(String hash, String password) {
		return BCrypt.checkpw(password, hash);
	}

	public static boolean checkHash(String hash, char[] password) {
		return checkHash(hash, String.copyValueOf(password));
	}

	public static UUID getNextUUID(String uuid) {
		UUID id = UUID.fromString(uuid);
		return getNextUUID(id);
	}
	
	public static UUID getNextUUID(UUID uuid) {
		return new UUID(uuid.getMostSignificantBits()+1, uuid.getLeastSignificantBits());
	}

	public static UUID genUserToken() {
		return UUID.randomUUID();
	}
}
