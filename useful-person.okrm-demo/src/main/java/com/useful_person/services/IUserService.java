package com.useful_person.services;

import java.util.List;

import com.useful_person.domain.User;

public interface IUserService {

	User save(User user);

	boolean delete(User user);
	
	User findByUuid(String uuid);
	
	boolean isExistUsername(String username);
	
	List<User> findByUsername(String username);

	List<User> findByNickname(String nickname);

}
