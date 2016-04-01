package com.web.socket.auth.model;

import org.junit.Test;

import com.web.socket.auth.core.model.Utils;

import static org.junit.Assert.*;

import java.util.UUID;

public class UtilsTest {

	@Test
	public void testHash() {
		String hash = Utils.getHash("password");
		System.out.println("Hash 1: " + hash);
		String hash2 = Utils.getHash(new char[]{'p','a','s','s','w','o','r','d'});
		System.out.println("Hash 2: " + hash);
		assertTrue(hash.equals(hash2));
		hash2 = Utils.getHash(new char[]{'p','a','s','s','w','o','r','d','2'});
		assertFalse(hash.equals(hash2));
		assertTrue("$2a$10$2gBPHiWvGGK2767EYDj2eOz09A4w2Gwc6INjoW01kTaQsRud1idvy".equals(hash));
	}

	@Test
	public void testUUID() {
		UUID uuid = UUID.fromString("715c13b3-881a-9c97-b853-10be585a9747");
		UUID uuid1 = UUID.fromString("715c13b3-881a-9c98-b853-10be585a9747");
		UUID uuid2 = Utils.getNextUUID(uuid);
		UUID uuid3 = Utils.getNextUUID(uuid2);
		
		assertTrue(uuid1.equals(uuid2));
		assertTrue(uuid3.compareTo(uuid2)>0);
		assertTrue(uuid1.compareTo(uuid2)==0);
		assertTrue(uuid2.compareTo(uuid3)<0);
	}

	@Test
	public void genHashs() {
		System.out.println(Utils.getHash("123123"));
		System.out.println(Utils.getHash("qwerty"));
		System.out.println(Utils.getHash("qwerty123"));
	}
}
