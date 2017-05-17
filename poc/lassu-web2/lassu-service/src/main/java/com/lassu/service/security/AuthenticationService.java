/**
 * 
 */
package com.lassu.service.security;

import com.lassu.common.model.User;

/**
 * @author abhinab
 *
 */
public interface AuthenticationService {

	User getUserDetailByUsername(String username);
	void update(User user);
	
}
