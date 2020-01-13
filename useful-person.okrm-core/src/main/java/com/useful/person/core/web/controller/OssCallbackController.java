/**
 * 
 */
package com.useful.person.core.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author peter
 *
 */
@Slf4j
@RestController
@RequestMapping("/oss")
public class OssCallbackController {

	@GetMapping("/callback")
	public String callback(@RequestParam String var1, @RequestParam String var2) {
		log.info("var1=" + var1);
		log.info("var2=" + var2);
		return "welcome";
	}
}
