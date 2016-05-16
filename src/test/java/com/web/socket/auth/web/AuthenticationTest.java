package com.web.socket.auth.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.socket.auth.WebsocketauthApplication;
import com.web.socket.auth.core.db.DBToken;
import com.web.socket.auth.core.db.TokenRepository;
import com.web.socket.auth.core.model.AuthRequest;
import com.web.socket.auth.core.model.AuthRequestData;
import com.web.socket.auth.core.model.AuthResponse;
import com.web.socket.auth.core.model.MessageTypes;
import com.web.socket.auth.core.model.Type;
import com.web.socket.auth.core.model.User;
import com.web.socket.auth.core.model.Utils;
import com.web.socket.auth.core.security.AuthenticationBean;
import static org.junit.Assert.*;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebsocketauthApplication.class)
@ActiveProfiles("dev")
public class AuthenticationTest {

    @Autowired
    private AuthenticationBean authenticationBean;

	@Autowired
	private TokenRepository tokenRepository;

    @Test
    public void testAuthentication1() {
    	//test db
    	assertEquals(0, tokenRepository.count());
    	
    	//test auth bean
    	AuthRequest request = new AuthRequest();
    	String seq_id = Utils.genUserToken().toString();
    	request.setSequence_id(seq_id);
    	request.setType(Type.LOGIN_CUSTOMER);
    	AuthRequestData data = new AuthRequestData();
    	data.setEmail("user");
    	data.setPassword(new char[]{'1','2','3','1','2','3'});
    	request.setData(data);
    	AuthResponse response = authenticationBean.authorize(request, new User());
    	assertEquals(Type.CUSTOMER_API_TOKEN, response.getType());
    	assertEquals(Utils.getNextUUID(seq_id).toString(), response.getSequence_id());
    	assertNotNull(response.getData().getApi_token());
    	assertNotNull(response.getData().getApi_token_expiration_date());
    	
    	//test db
    	assertEquals(1, tokenRepository.count());
    	List<DBToken> tokens = tokenRepository.findAll();
    	DBToken token = tokens.get(0);
    	assertEquals(response.getData().getApi_token(), token.getToken());
    }

    @Test
    public void testAuthentication2() {
    	AuthRequest request = new AuthRequest();
    	String seq_id = Utils.genUserToken().toString();
    	request.setSequence_id(seq_id);
    	request.setType(Type.LOGIN_CUSTOMER);
    	AuthRequestData data = new AuthRequestData();
    	data.setEmail("user");
    	data.setPassword(new char[]{'1','2','3','1','2'});
    	request.setData(data);
    	AuthResponse response = authenticationBean.authorize(request, new User());
    	assertEquals(Type.CUSTOMER_ERROR, response.getType());
    	assertEquals(Utils.getNextUUID(seq_id).toString(), response.getSequence_id());
    	assertEquals(response.getData().getError_code(), MessageTypes.customerNotFound);
    }

    @Test
    public void testAuthentication3() {
    	//test auth bean
    	AuthRequest request = new AuthRequest();
    	String seq_id = Utils.genUserToken().toString();
    	request.setSequence_id(seq_id);
    	request.setType(Type.LOGIN_CUSTOMER);
    	AuthRequestData data = new AuthRequestData();
    	data.setEmail("user");
    	data.setPassword(new char[]{'1','2','3','1','2','3'});
    	request.setData(data);
    	// first
    	AuthResponse response1 = authenticationBean.authorize(request, new User());
    	// second
    	AuthResponse response2 = authenticationBean.authorize(request, new User());
    	assertNotEquals(response1.getData().getApi_token(), response2.getData().getApi_token());
    }
    
}
