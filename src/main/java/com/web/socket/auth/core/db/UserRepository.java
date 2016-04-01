package com.web.socket.auth.core.db;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DBUser, Long> {

	DBUser findByEmail(String email);
	
	DBUser findByUserId(Long id);
}
