package com.web.socket.auth.core.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DBUser, Long> {

	List<DBUser> findByEmail(String email);
	
	DBUser findByUserId(Long id);
}
