package com.useful_person.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful_person.domain.User;
import com.useful_person.repository.UserRepository;
import com.useful_person.services.IUserService;

@Service("userServices")
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public boolean delete(User user) {
		userRepository.delete(user);
		return true;
	}

	@Override
	public User findByUuid(String uuid) {
		Optional<User> optionalUser = userRepository.findById(uuid);
		User user = null;
		try {
			user = optionalUser.get();
		} catch (Exception e) {
			user = null;
		};
		return user;
	}

	@Override
	public List<User> findByUsername(String username) {
		return userRepository.findByUsernameLike(username);
	}

	@Override
	public List<User> findByNickname(String nickname) {
		return userRepository.findByNicknameLike(nickname);
	}
	
	@Override
	public boolean isExistUsername(String username) {
		List<User> users = userRepository.findByUsername(username);
		return users.size() >= 0;
	}
}
