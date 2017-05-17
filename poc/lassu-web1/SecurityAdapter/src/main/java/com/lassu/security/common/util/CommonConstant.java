/**
 * 
 */
package com.lassu.security.common.util;

/**
 * @author abhinab
 *
 */
public interface CommonConstant {
	

	int DB_RETRY_COUNT = 3;
	int DB_RETRY_DELAY = 2000;
	
	String COMPONENT_AUTHAUDIT_PROCESSOR ="authauditconsumer";
	String AUTH_TOKEN = "2ebfc0e6-7e32-4c95-81a7-f9b0980f7d04";
	String URL_MONITOR = "/admin/security/target/internal-communication/" + AUTH_TOKEN;
	String URL_MONITOR_RESP = "/admin/security/src/internal-communication/" + AUTH_TOKEN;

}
