/**
 * 
 */
package com.lassu.security.service;

import com.lassu.security.common.domain.RespAuth;

/**
 * @author abhinab
 *
 */
public interface AuthAuditService {

	void performAudit(RespAuth respAuth);
	
}
