/**
 * 
 */
package com.lassu.service.security;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

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

	private InternalRestTemplate internalRestTemplate = InternalRestTemplate.getInstance();

	@Override
	public void accept(Event<UsernamePasswordToken> event) {
		UsernamePasswordToken usernamePasswordToken = event.getData();
		try {
			usernamePasswordToken.setPassword(CommonUtil.encrypt(usernamePasswordToken.getPassword()));
			internalRestTemplate.postForEntity("http://localhost:9081" + CommonConstant.URL_MONITOR,
					usernamePasswordToken, RespAuth.class).getBody();
		} catch (DataLengthException | IllegalStateException | InvalidCipherTextException | ResourceAccessException e) {
			e.printStackTrace();
		}
	}

}
