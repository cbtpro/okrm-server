package com.useful.person.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.properties.SigninType;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author peter
 *
 */
@Slf4j
@Order(0)
@Component("okrmAuthenticationSuccessHandler")
public class OkrmAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		UserInfo userInfo = (UserInfo) authentication.getPrincipal();
		log.info("登录成功：userInfo：" + userInfo.toString());
		if (SigninType.JSON.equals(securityProperties.getBrowser().getSigninType())) {
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().write(objectMapper.writeValueAsString(userInfo));
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
