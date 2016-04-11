package com.web.socket.auth.web;

import java.time.ZoneId;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.WebUtils;

import com.web.socket.auth.core.model.AuthRequest;
import com.web.socket.auth.core.model.AuthResponse;
import com.web.socket.auth.core.model.User;
import com.web.socket.auth.core.security.AuthenticationBean;

@Controller
public class AuthController {

	private static Log log = LogFactory.getLog(AuthenticationBean.class.getName());

	@Autowired
	private AuthenticationBean authBean;
	
	@Autowired
	private User user;

	@MessageMapping("/auth")
	@SendToUser(destinations="/response/auth", broadcast=false)
    public AuthResponse auth(AuthRequest request, ZoneId zoneId) throws Exception {
		log.info("request: " + request);
		log.info("zoneId: " + zoneId);
		AuthResponse response = authBean.authorize(request, user);
		log.info("response: " + response);
		log.info("user: " + user);
        return response;
    }
}
