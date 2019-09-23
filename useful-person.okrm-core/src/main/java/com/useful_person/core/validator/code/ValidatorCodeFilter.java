package com.useful_person.core.validator.code;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

public class ValidatorCodeFilter extends OncePerRequestFilter {

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (StringUtils.pathEquals("/authentication/form", request.getRequestURI())
				&& StringUtils.pathEquals(request.getMethod(), "POST")) {
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidatorCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
		if (!StringUtils.hasText(codeInRequest)) {
			throw new ValidatorCodeException("验证码不能为空");
		}
//		ImageCode codeImageInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidatorCodeController.SESSION_KEY);
//		if (codeImageInSession == null) {
//			throw new ValidatorCodeException("验证码不存在");
//		}
//		if (codeImageInSession.isExpired()) {
//			throw new ValidatorCodeException("验证码已过期");
//		}
//		if (!StringUtils.pathEquals(codeImageInSession.getCode(), codeInRequest)) {
//			throw new ValidatorCodeException("验证码不匹配");
//		}
		String codeImageInSession = (String) sessionStrategy.getAttribute(request, ValidatorCodeController.SESSION_KEY);
		if (codeImageInSession == null) {
			throw new ValidatorCodeException("验证码不存在");
		}
		if (!StringUtils.pathEquals(codeImageInSession.toLowerCase(), codeInRequest.toLowerCase())) {
			throw new ValidatorCodeException("验证码不匹配");
		}
		sessionStrategy.removeAttribute(request, ValidatorCodeController.SESSION_KEY);
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

}
