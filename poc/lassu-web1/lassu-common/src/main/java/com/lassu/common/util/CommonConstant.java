/**
 * 
 */
package com.lassu.common.util;


/**
 * @author abhinab
 *
 */
public interface CommonConstant {

	String URL_CSS = "/css/**";
	String URL_JS = "/js/**";
	String URL_IMG = "/img/**";
	
	String AUTH_CODE_LOGIN_DISABLED = "UD";
	String AUTH_CODE_LOGIN_INVALID = "NA";
	String AUTH_CODE_LOGIN_DEACTIVE = "UN";
	String AUTH_CODE_LOGIN_INSECURE = "UI";
	
	String URL_LOGIN = "/login";
	String URL_LOGIN_ERROR_DISABLED = URL_LOGIN + "?error=" + AUTH_CODE_LOGIN_DISABLED;
	String URL_LOGIN_ERROR_INVALID = URL_LOGIN + "?error=" + AUTH_CODE_LOGIN_INVALID;
	String URL_LOGIN_ERROR_DEACTIVE = URL_LOGIN + "?error=" + AUTH_CODE_LOGIN_DEACTIVE;
	String URL_LOGIN_ERROR_INSECURE = URL_LOGIN + "?error=" + AUTH_CODE_LOGIN_INSECURE;
	String URL_LOGOUT = "/logout";
	
	String URL_DEFAULT_SUCCESS = "/";
	String COOKIE_JSESSIONID = "JSESSIONID";
	int MAX_SESSION = 1;

	int DB_RETRY_COUNT = 3;
	int DB_RETRY_DELAY = 2000;
	
	String COMPONENT_POSTAUTH_PROCESSOR ="postauthconsumer";
	
	String AUTH_TOKEN = System.getProperty("AUTH_TOKEN");
	String SECURITY_ADAPTER_HOST = System.getProperty("partner.site");
	String SECURITY_ADAPTER_URL = SECURITY_ADAPTER_HOST + "/admin/security/adapter/internal-communication/" + AUTH_TOKEN; 
	

}
