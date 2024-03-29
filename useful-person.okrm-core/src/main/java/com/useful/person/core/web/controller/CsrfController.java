package com.useful.person.core.web.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author peter
 *
 */
@RestController
public class CsrfController {

	/**
	 * 获取token防止CSRF攻击
	 * @param token
	 * @return
	 */
	@GetMapping("/csrf")
	public CsrfToken csrf(CsrfToken token) {
		return token;
	}
}