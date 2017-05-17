/**
 * 
 */
package com.lassu.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lassu.security.common.domain.User;
import com.lassu.security.common.domain.UsernamePasswordToken;
import com.lassu.security.common.util.CommonConstant;
import com.lassu.security.common.util.CommonUtil;
import com.lassu.security.dao.AuthCheckDao;
import com.lassu.security.service.AuthCheckService;

import reactor.bus.Event;
import reactor.bus.EventBus;

/**
 * @author abhinab
 *
 */
@Service
public class AuthCheckServiceImpl implements AuthCheckService {

	@Autowired
	private AuthCheckDao authCheckDao;
	
	@Autowired
	private EventBus eventBus;
	
	private static BCryptPasswordEncoder passwordEncoder;
	
	static {
		passwordEncoder = new BCryptPasswordEncoder();
	}
	
	/* (non-Javadoc)
	 * @see com.lassu.security.service.AuthCheckService#performCheck(com.lassu.security.common.domain.UsernamePasswordToken)
	 */
	@Override
	public void performCheck(UsernamePasswordToken usernamePasswordToken) throws Exception {
		User user = authCheckDao.getUserUsername(usernamePasswordToken.getUsername());
		String decryptedPassword = CommonUtil.decrypt(usernamePasswordToken.getPassword());
		boolean checkResult = passwordEncoder.matches(decryptedPassword, user.getPassword());
		if (checkResult) {
			eventBus.notify(CommonConstant.COMPONENT_POSTAUTHCHK_PROCESSOR, Event.wrap(usernamePasswordToken));
		}
	}

}
