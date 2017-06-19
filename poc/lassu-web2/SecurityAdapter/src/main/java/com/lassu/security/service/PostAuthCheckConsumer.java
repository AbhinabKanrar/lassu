/**
 * 
 */
package com.lassu.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lassu.security.common.domain.AuditData;
import com.lassu.security.common.domain.RespAuth;
import com.lassu.security.common.domain.User;
import com.lassu.security.common.domain.UsernamePasswordToken;
import com.lassu.security.common.util.CommonConstant;
import com.lassu.security.common.util.CommonUtil;
import com.lassu.security.common.util.InternalRestTemplate;
import com.lassu.security.dao.AuthCheckDao;

import reactor.bus.Event;
import reactor.fn.Consumer;

/**
 * @author abhinab
 *
 */
@Component
public class PostAuthCheckConsumer implements Consumer<Event<UsernamePasswordToken>> {

	@Autowired
	private AuthCheckDao authCheckDao;
	
	private InternalRestTemplate internalRestTemplate = InternalRestTemplate.getInstance();
	
	@Override
	public void accept(Event<UsernamePasswordToken> event) {
		UsernamePasswordToken usernamePasswordToken = event.getData();
		try {
			User user = authCheckDao.getUserUsername(usernamePasswordToken.getUsername());
			AuditData auditData = new AuditData();
			RespAuth respAuth = new RespAuth();
			respAuth.setSource(CommonUtil.getHostname());
			respAuth.setUsername(user.getUsername());
			
			auditData.setUser(user);
			auditData.setRespAuth(respAuth);
			
			authCheckDao.update(auditData);
			
			internalRestTemplate.postForEntity(CommonConstant.SECURITY_ADAPTER_HOST + CommonConstant.URL_MONITOR_RESP,
					new RespAuth(CommonUtil.getHostname(), usernamePasswordToken.getUsername()), String.class).getBody();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
