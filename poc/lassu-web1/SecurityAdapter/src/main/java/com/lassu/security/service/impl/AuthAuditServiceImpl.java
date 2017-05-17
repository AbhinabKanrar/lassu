/**
 * 
 */
package com.lassu.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lassu.security.common.domain.AuditData;
import com.lassu.security.common.domain.RespAuth;
import com.lassu.security.common.domain.User;
import com.lassu.security.common.util.CommonConstant;
import com.lassu.security.dao.AuthAuditDao;
import com.lassu.security.service.AuthAuditService;

import reactor.bus.Event;
import reactor.bus.EventBus;

/**
 * @author abhinab
 *
 */
@Service
public class AuthAuditServiceImpl implements AuthAuditService {

	@Autowired
	private AuthAuditDao authCheckDao;
	
	@Autowired
	private EventBus eventBus;
	
	/**
	 * 
	 */
	@Override
	public void performAudit(RespAuth respAuth) {
		User user = authCheckDao.getUserByUsername(respAuth.getUsername());
		if (user != null) {
			AuditData auditData = new AuditData();
			auditData.setRespAuth(respAuth);
			auditData.setUser(user);
			eventBus.notify(CommonConstant.COMPONENT_AUTHAUDIT_PROCESSOR, Event.wrap(auditData));
		}
	}

}
