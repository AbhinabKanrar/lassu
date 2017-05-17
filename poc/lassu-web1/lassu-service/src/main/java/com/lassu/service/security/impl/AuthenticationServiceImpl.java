/**
 * 
 */
package com.lassu.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lassu.common.model.User;
import com.lassu.dao.security.AuthenticationDao;
import com.lassu.service.security.AuthenticationService;

/**
 * @author abhinab
 *
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationDao authenticationDao;
	
	/* (non-Javadoc)
	 * @see com.lassu.service.security.AuthenticationService#getUserDetailByUsername(java.lang.String)
	 */
	@Override
	public User getUserDetailByUsername(String username) {
		return authenticationDao.getUserUsername(username);
	}

	/* (non-Javadoc)
	 * @see com.lassu.service.security.AuthenticationService#update(com.lassu.common.model.User)
	 */
	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

}
