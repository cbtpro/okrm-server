package com.useful_person.core.authentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful_person.core.authentication.domain.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String> {

	UserInfo findByUsername(String username);

	List<UserInfo> findByNickname(String nickname);

	List<UserInfo> findByNicknameLike(String nickname);
	
	List<UserInfo> findByUsernameLike(String username);
}
