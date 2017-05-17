/**
 * 
 */
package com.lassu.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lassu.security.common.domain.RespAuth;
import com.lassu.security.common.domain.UsernamePasswordToken;
import com.lassu.security.common.util.CommonConstant;
import com.lassu.security.service.AuthCheckService;

/**
 * @author abhinab
 *
 */
@RestController
@RequestMapping("/admin/security")
public class MonitoringRouter {

	@Autowired
	private AuthCheckService authCheckService;

	@PostMapping(value = "/target/internal-communication/"
			+ CommonConstant.AUTH_TOKEN, headers = "Accept=application/xml", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private @ResponseBody ResponseEntity<RespAuth> check(final @RequestBody UsernamePasswordToken usernamePasswordToken)
			throws Exception {
		if (!valid(usernamePasswordToken)) {
			return new ResponseEntity<RespAuth>(HttpStatus.EXPECTATION_FAILED);
		}
		authCheckService.performCheck(usernamePasswordToken);
		return new ResponseEntity<RespAuth>(HttpStatus.OK);
	}

	private boolean valid(UsernamePasswordToken usernamePasswordToken) {
		if (usernamePasswordToken != null && usernamePasswordToken.getUsername() != null
				&& !usernamePasswordToken.getUsername().isEmpty() && usernamePasswordToken.getPassword() != null
				&& !usernamePasswordToken.getPassword().isEmpty()) {
			return true;
		}
		return false;
	}

}
