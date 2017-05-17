/**
 * 
 */
package com.lassu.service.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.lassu.common.model.UsernamePasswordToken;

import reactor.bus.Event;
import reactor.fn.Consumer;

/**
 * @author abhinab
 *
 */
@Component
public class PostAuthConsumer implements Consumer<Event<UsernamePasswordToken>> {

	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public void accept(Event<UsernamePasswordToken> event) {
		UsernamePasswordToken usernamePasswordToken = event.getData();
		usernamePasswordToken.setPassword(passwordEncoder.encode(usernamePasswordToken.getPassword()));
	}

}
