/**
 * 
 */
package com.lassu.web.router;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lassu.common.util.CommonConstant;

/**
 * @author abhinab
 *
 */
@Controller
public class SecurityRouter {

	@GetMapping(CommonConstant.URL_LOGIN)
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:" + CommonConstant.URL_DEFAULT_SUCCESS;
		}
		if (error != null) {
			if (error.equalsIgnoreCase(CommonConstant.AUTH_CODE_LOGIN_DEACTIVE)) {
				model.addAttribute("msg", "Your account is deactivated");
			} else if (error.equalsIgnoreCase(CommonConstant.AUTH_CODE_LOGIN_DISABLED)) {
				model.addAttribute("msg", "Your account is disabled");
			} else if (error.equalsIgnoreCase(CommonConstant.AUTH_CODE_LOGIN_INSECURE)) {
				model.addAttribute("msg",
						"The login and password you have used appears to have been used on another website. This is not recommended. Please choose a unique password for this website");
			} else if (error.equalsIgnoreCase(CommonConstant.AUTH_CODE_LOGIN_INVALID)) {
				model.addAttribute("msg", "Invalid username and/or password");
			}
		}
		return "login";
	}

	@GetMapping(CommonConstant.URL_DEFAULT_SUCCESS)
	public String loginSuccess(Model model) {
		return "dashboard";
	}

}
