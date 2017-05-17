/**
 * 
 */
package com.lassu.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lassu.security.common.domain.AuditData;
import com.lassu.security.dao.AuthAuditDao;

import reactor.bus.Event;
import reactor.fn.Consumer;

/**
 * @author abhinab
 *
 */
@Component
public class AuthAuditConsumer implements Consumer<Event<AuditData>> {

	@Autowired
	private AuthAuditDao authAuditDao;
	
	@Override
	public void accept(Event<AuditData> event) {
		AuditData auditData = event.getData();
		authAuditDao.update(auditData);
	}

}
