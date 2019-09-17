package com.useful_person.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful_person.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

	List<User> findByUsername(String username);

	List<User> findByNickname(String nickname);

	List<User> findByNicknameLike(String nickname);
	
	List<User> findByUsernameLike(String username);
}
