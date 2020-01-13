package com.useful.person.browser.authentication;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.useful.person.browser.support.SimpleResponse;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.properties.SigninType;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author peter
 *
 */
@Slf4j
@Component("okrmAuthenticationFailureHandler")
public class OkrmAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		Enumeration<String> parameterNames = request.getParameterNames();
		StringBuffer logSb = new StringBuffer("");
		while(parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String  parameterValue = request.getParameter(parameterName);
			if ("password".equals(parameterName)) {
				parameterValue = "*".repeat(parameterValue.length());
			}
			if (logSb.length() > 0) {
				logSb.append(", ");
			}
			logSb.append(parameterName + ": " + parameterValue);
		}
		log.info("验证失败，参数：" + logSb);
		if (SigninType.JSON.equals(securityProperties.getBrowser().getSigninType())) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
		} else {
			super.onAuthenticationFailure(request, response, exception);
		}
	}

}
