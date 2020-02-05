package com.useful.person.core.authentication.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.useful.person.core.authentication.exception.MobileNotRegisteredException;
import com.useful.person.core.authentication.exception.UsernameNotExistException;
import com.useful.person.core.authentication.services.IUserService;
import com.useful.person.core.domain.UserInfo;

/**
 * 
 * @author peter
 *
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUserService userServices;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserInfo user = userServices.findByUsername(username);
		if (user == null) {
			throw new UsernameNotExistException(username);
		}
		return user;
	}

	public UserDetails loadUserByMobile(String mobile) throws MobileNotRegisteredException {
		UserInfo user = userServices.findByMobile(mobile);
		if (user == null) {
			throw new MobileNotRegisteredException(mobile);
		}
		return user;
	}
}
