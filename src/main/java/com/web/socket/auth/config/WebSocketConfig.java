package com.web.socket.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Value( "${async.send.timeout}" )
	private long asyncSendTimeout;
	
	@Value( "${max.session.idle.timeout}" )
	private long maxSessionIdleTimeout;
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.setUserDestinationPrefix("/user");
		config.setApplicationDestinationPrefixes("/app");
		config.enableSimpleBroker("/response");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/auth").withSockJS();
	}

	@Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        container.setAsyncSendTimeout(asyncSendTimeout);
        container.setMaxSessionIdleTimeout(maxSessionIdleTimeout);
        return container;
    }
}