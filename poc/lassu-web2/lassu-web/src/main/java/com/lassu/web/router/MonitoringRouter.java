package com.lassu.web.router;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lassu.common.model.RespAuth;
import com.lassu.common.model.UsernamePasswordToken;
import com.lassu.common.util.CommonConstant;

@RestController
@RequestMapping("/admin/target")
public class MonitoringRouter {

	@PostMapping(value = "/internal-communication/"
			+ CommonConstant.AUTH_TOKEN, headers = "Accept=application/xml", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<RespAuth> processInterProcessCommunication() {
		System.out.println("pwd: ->  ");
		return new ResponseEntity<RespAuth>(HttpStatus.OK);
	}

}
