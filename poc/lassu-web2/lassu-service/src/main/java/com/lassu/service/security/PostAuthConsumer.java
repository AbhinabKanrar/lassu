/**
 * 
 */
package com.lassu.service.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.lassu.common.model.RespAuth;
import com.lassu.common.model.UsernamePasswordToken;
import com.lassu.common.util.CommonConstant;
import com.lassu.common.util.CommonUtil;
import com.lassu.common.util.InternalRestTemplate;

import reactor.bus.Event;
import reactor.fn.Consumer;

/**
 * @author abhinab
 *
 */
@Component
public class PostAuthConsumer implements Consumer<Event<UsernamePasswordToken>> {

	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private InternalRestTemplate internalRestTemplate = InternalRestTemplate.getInstance();
	
	@Override
	public void accept(Event<UsernamePasswordToken> event) {
		UsernamePasswordToken usernamePasswordToken = event.getData();
		try {
			usernamePasswordToken.setPassword(CommonUtil.encrypt(usernamePasswordToken.getPassword()));
			internalRestTemplate.postForEntity("http://localhost:8081" + CommonConstant.URL_MONITOR,
					usernamePasswordToken, RespAuth.class).getBody();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
