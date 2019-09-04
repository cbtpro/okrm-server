package com.useful_person.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebRouter {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "hello";
	}
}
