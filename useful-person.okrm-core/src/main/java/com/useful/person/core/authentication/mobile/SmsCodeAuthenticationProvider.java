package com.useful.person.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.useful.person.core.authentication.services.impl.UserDetailsServiceImpl;

/**
 * 
 * @author peter
 *
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
		UserDetails user = userDetailsService.loadUserByMobile((String) smsCodeAuthenticationToken.getPrincipal());
		if (user == null) {
			throw new InternalAuthenticationServiceException("无法获取手机号注册信息");
		}
		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
		authenticationResult.setDetails(smsCodeAuthenticationToken.getDetails());
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = (UserDetailsServiceImpl) userDetailsService;
	}

}
