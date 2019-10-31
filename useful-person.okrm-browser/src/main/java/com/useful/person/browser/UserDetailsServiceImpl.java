package com.useful.person.browser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.useful.person.core.authentication.domain.UserInfo;
import com.useful.person.core.authentication.services.IUserService;

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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 用户是否未被逻辑删除
		boolean enabled = true;
		// 账号没过期
		boolean accountNonExpired = true;
		// 密码没过期
		boolean credentialsNonExpired = true;
		// 账号未被锁定
		boolean accountNonLocked = true; 
		UserInfo user = userServices.findByUsername(username);
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("normal");
		return new User(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}
}
