/**
 * 
 */
package com.lassu.security.service;

import com.lassu.security.common.domain.UsernamePasswordToken;

/**
 * @author abhinab
 *
 */
public interface AuthCheckService {

	void performCheck(UsernamePasswordToken usernamePasswordToken) throws Exception;
	
}
