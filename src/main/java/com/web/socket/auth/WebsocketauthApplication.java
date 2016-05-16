package com.web.socket.auth;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.web.socket.auth.core.security.AuthenticationBean;

@SpringBootApplication
@Profile({"dev", "production"})
public class WebsocketauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketauthApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
	 SessionLocaleResolver slr = new SessionLocaleResolver();
	 slr.setDefaultLocale(Locale.US); // Set default Locale as US
	 return slr;
	}
	 
	@Bean
	public ResourceBundleMessageSource messageSource() {
	 ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	 source.setBasenames("i18n/messages");  // name of the resource bundle 
	 source.setUseCodeAsDefaultMessage(true);
	 return source;
	}

	@Bean
	public AuthenticationBean authenticationBean() {
		AuthenticationBean authBean = new AuthenticationBean();
		return authBean;
	}
}
