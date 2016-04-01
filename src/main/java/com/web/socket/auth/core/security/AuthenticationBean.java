package com.web.socket.auth.core.security;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;

import com.web.socket.auth.core.Notification;
import com.web.socket.auth.core.db.DBToken;
import com.web.socket.auth.core.db.DBUser;
import com.web.socket.auth.core.db.TokenRepository;
import com.web.socket.auth.core.db.UserRepository;
import com.web.socket.auth.core.model.AuthRequest;
import com.web.socket.auth.core.model.AuthResponse;
import com.web.socket.auth.core.model.MessageTypes;
import com.web.socket.auth.core.model.Type;
import com.web.socket.auth.core.model.User;
import com.web.socket.auth.core.model.Utils;

public class AuthenticationBean {
	
	private static Log log = LogFactory.getLog(AuthenticationBean.class.getName());

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
    private MessageSource messageSource;

	@Value( "${max.session.idle.timeout}" )
	private long maxSessionIdleTimeout;

	public AuthResponse authorize(AuthRequest request, User user) {
		AuthResponse result = null;
		Locale locale = LocaleContextHolder.getLocale();
		switch (request.getType()) {
			case Type.LOGIN_CUSTOMER:
				// validate input
				Notification note = validation(request, locale);
				if (note.hasErrors()) {
					result = new AuthResponse(
							Type.CUSTOMER_ERROR,
							note.getErrorTypes(),
							note.getErrorDescriptions());
				} else {
					// is user exist
					DBUser dbuser = userRepository.findByEmail(request.getData().getEmail());
					if (dbuser == null) {
						result = new AuthResponse(
								Type.CUSTOMER_ERROR,
								MessageTypes.customerNotFound,
								messageSource.getMessage(
					    				MessageTypes.customerNotFound,
					    				null,
					    				locale
									));
					} else {
						// check password
						if (Utils.checkHash(dbuser.getPasswordHash(), request.getData().getPassword())) {
							String token = Utils.genUserToken().toString();
							DBToken dbtoken = new DBToken(token, dbuser);
							tokenRepository.save(dbtoken);
							log.info("tokens count: " + tokenRepository.count());
							log.info("maxSessionIdleTimeout: " + maxSessionIdleTimeout);
							log.info("datetime: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
							log.info("datetime: " + LocalDateTime.now().plusSeconds(maxSessionIdleTimeout/1000).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
							result = new AuthResponse(
								Type.CUSTOMER_API_TOKEN,
								Utils.getNextUUID(request.getSequence_id()).toString(),
								token,
								LocalDateTime.now().plusSeconds(maxSessionIdleTimeout/1000));
							user.setEmail(request.getData().getEmail());
							user.setToken(token);
						} else {
							result = new AuthResponse(
								Type.CUSTOMER_ERROR,
								MessageTypes.customerNotFound,
								messageSource.getMessage(
					    				MessageTypes.customerNotFound,
					    				null,
					    				locale
									));
						}
					}
				}
				break;
			default:
				result = new AuthResponse(
						Type.CUSTOMER_ERROR,
						MessageTypes.requestTypeUndefined,
						messageSource.getMessage(
			    				MessageTypes.requestTypeUndefined,
			    				null,
			    				locale
							));
				break;
		}
		return result;
	}

	public Notification validation(AuthRequest request, Locale locale) {
		Notification note = new Notification();
		if (!StringUtils.hasText(request.getData().getEmail())) {
			note.addError(
					MessageTypes.emptyEmail,
					messageSource.getMessage(
    				MessageTypes.emptyEmail,
    				null,
    				locale
				));
		}
		if (request.getData().getPassword().length == 0) {
			note.addError(
					MessageTypes.emptyPassword,
					messageSource.getMessage(
    				MessageTypes.emptyPassword,
    				null,
    				locale
				));
		}
		boolean isUuid = false;
		try {
			UUID.fromString(request.getSequence_id());
			isUuid = true;
		} catch (IllegalArgumentException ex) {
			isUuid = false;
		}
		if (!isUuid) {
			note.addError(
					MessageTypes.invalidSequenceId,
					messageSource.getMessage(
    				MessageTypes.invalidSequenceId,
    				null,
    				locale
				));
		}
		return note;
		
	}
}
