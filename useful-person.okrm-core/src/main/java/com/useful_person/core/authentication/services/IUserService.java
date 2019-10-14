package com.useful_person.core.authentication.services;

import java.util.List;

import com.useful_person.core.authentication.domain.UserInfo;

public interface IUserService {

	UserInfo register(UserInfo user);

	boolean delete(UserInfo user);
	
	UserInfo findByUuid(String uuid);
	
	boolean isExistUsername(String username);
	
	UserInfo findByUsername(String username);

	List<UserInfo> findByNickname(String nickname);

}
