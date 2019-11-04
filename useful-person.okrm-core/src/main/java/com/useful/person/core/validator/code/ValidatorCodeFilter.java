package com.useful.person.core.validator.code;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.validator.code.captcha.ImageCode;

/**
 * 
 * @author peter
 *
 */
public class ValidatorCodeFilter extends OncePerRequestFilter implements InitializingBean {

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	private AuthenticationFailureHandler authenticationFailureHandler;

	private Set<String> urls = new HashSet<>();

	private SecurityProperties securityProperties;

	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		String needCodeUrls = securityProperties.getCode().getImage().getUrl();
		String[] configUrls = needCodeUrls.split(",");
		for (String configUrl : configUrls) {
			urls.add(configUrl);
		}
		urls.add("/authentication/form");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean action = false;
		String requestUri = request.getRequestURI();
		for(String url:urls) {
			if (antPathMatcher.match(url, requestUri)) {
				action = true;
			}
		}
		if (action) {
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidatorCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public SessionStrategy getSessionStrategy() {
		return sessionStrategy;
	}

	public Set<String> getUrls() {
		return urls;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	public void setSessionStrategy(SessionStrategy sessionStrategy) {
		this.sessionStrategy = sessionStrategy;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}

	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE);
		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidatorCodeException("验证码不能为空");
		}
		ImageCode codeImageInSession = (ImageCode) sessionStrategy.getAttribute(request, SecurityConstants.DEFAULT_SESSION_KEY_IMAGE_CODE);
		if (codeImageInSession == null) {
			throw new ValidatorCodeException("验证码不存在");
		}
		if (codeImageInSession.isExpired()) {
			throw new ValidatorCodeException("验证码已过期");
		}
		if (!StringUtils.equalsIgnoreCase(codeImageInSession.getCode(), codeInRequest)) {
			throw new ValidatorCodeException("验证码不匹配");
		}
		sessionStrategy.removeAttribute(request, SecurityConstants.DEFAULT_SESSION_KEY_IMAGE_CODE);
	}

}