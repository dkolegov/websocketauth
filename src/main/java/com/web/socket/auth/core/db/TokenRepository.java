package com.web.socket.auth.core.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<DBToken, Long> {

}
