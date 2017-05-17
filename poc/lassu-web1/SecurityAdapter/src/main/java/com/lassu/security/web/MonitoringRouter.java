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
import com.lassu.security.common.util.CommonConstant;
import com.lassu.security.service.AuthAuditService;

/**
 * @author abhinab
 *
 */
@RestController
@RequestMapping("/admin/security")
public class MonitoringRouter {
	
	@Autowired
	private AuthAuditService authAuditService;

	@PostMapping(value = "/src/internal-communication/"
			+ CommonConstant.AUTH_TOKEN, headers = "Accept=application/xml", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private @ResponseBody ResponseEntity<RespAuth> check(final @RequestBody RespAuth respAuth) {
		if (!valid(respAuth)) {
			return new ResponseEntity<RespAuth>(HttpStatus.EXPECTATION_FAILED);
		}
		authAuditService.performAudit(respAuth);
		return new ResponseEntity<RespAuth>(HttpStatus.OK);
	}

	private boolean valid(RespAuth respAuth) {
		if (respAuth != null && !respAuth.getUsername().isEmpty()) {
			return true;
		}
		return false;
	}

}
