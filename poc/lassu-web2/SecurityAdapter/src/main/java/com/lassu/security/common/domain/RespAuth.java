/**
 * 
 */
package com.lassu.security.common.domain;

import java.io.Serializable;

/**
 * @author abhinab
 *
 */
public class RespAuth implements Serializable {

	private static final long serialVersionUID = -2098640867481904784L;

	private String username;
	private String source;

	public RespAuth() {
	}

	public RespAuth(String source, String username) {
		this.source = source;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
