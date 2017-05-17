/**
 * 
 */
package com.lassu.web.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.lassu.common.model.UserStatus;
import com.lassu.common.model.UsernamePasswordToken;
import com.lassu.common.util.CommonConstant;
import com.lassu.common.util.CommonUtil;

import reactor.bus.Event;
import reactor.bus.EventBus;

/**
 * @author abhinab
 *
 */
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private EventBus eventBus;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		handle(request, response, authentication);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 */
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		UserStatus userStatus = (UserStatus) WebUtils.getSessionAttribute(request, "userStatusToken");
		if (userStatus != null) {
			if (userStatus == UserStatus.ACTIVE) {
				UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
						request.getParameter("username"), request.getParameter("password"), CommonUtil.getHostname());
				eventBus.notify(CommonConstant.COMPONENT_POSTAUTH_PROCESSOR, Event.wrap(usernamePasswordToken));
				redirectStrategy.sendRedirect(request, response, CommonConstant.URL_DEFAULT_SUCCESS);
			} else {
				request.getSession().invalidate();
				if (userStatus == UserStatus.DISABLED) {
					redirectStrategy.sendRedirect(request, response, CommonConstant.URL_LOGIN_ERROR_DISABLED);
				} else if (userStatus == UserStatus.DEACTIVE) {
					redirectStrategy.sendRedirect(request, response, CommonConstant.URL_LOGIN_ERROR_DEACTIVE);
				} else if (userStatus == UserStatus.INSECURE) {
					redirectStrategy.sendRedirect(request, response, CommonConstant.URL_LOGIN_ERROR_INSECURE);
				}
			}
		} else {
			request.getSession().invalidate();
			redirectStrategy.sendRedirect(request, response, CommonConstant.URL_LOGIN_ERROR_INVALID);
		}
	}

}
