/**
 * 
 */
package com.lassu.security.common.domain;

/**
 * @author abhinab
 *
 */
public class AuditData {

	private RespAuth respAuth;
	private User user;

	public RespAuth getRespAuth() {
		return respAuth;
	}

	public void setRespAuth(RespAuth respAuth) {
		this.respAuth = respAuth;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
