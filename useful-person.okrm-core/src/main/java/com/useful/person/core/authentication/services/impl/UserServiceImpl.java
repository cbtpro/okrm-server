package com.useful.person.core.authentication.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.useful.person.core.authentication.domain.UserInfo;
import com.useful.person.core.authentication.exception.UsernameExistException;
import com.useful.person.core.authentication.repository.UserRepository;
import com.useful.person.core.authentication.services.IUserService;

/**
 * 
 * @author peter
 *
 */
@Service("userServices")
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserInfo register(UserInfo user) {
		String username = user.getUsername();
		boolean isExistUsername = isExistUsername(username);
		if (isExistUsername) {
			throw new UsernameExistException(username);
		}
		encryptPassword(user);
		return userRepository.save(user);
	}

	@Override
	public boolean delete(UserInfo user) {
		userRepository.delete(user);
		return true;
	}

	@Override
	public UserInfo findByUuid(String uuid) {
		return userRepository.findById(uuid).orElse(null);
	}

	@Override
	public UserInfo findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<UserInfo> findByNickname(String nickname) {
		return userRepository.findByNicknameLike(nickname);
	}

	@Override
	public boolean isExistUsername(String username) {
		UserInfo users = userRepository.findByUsername(username);
		return users != null;
	}

	private void encryptPassword(UserInfo user) {
		String password = user.getPassword();
		password = passwordEncoder.encode(password);
		user.setPassword(password);
	}
}