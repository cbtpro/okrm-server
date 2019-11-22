package com.useful.person.core.authentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.authentication.domain.SysUser;

/**
 * 
 * @author peter
 *
 */
public interface UserRepository extends JpaRepository<SysUser, String> {

	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return 用户
	 */
	SysUser findByUsername(String username);

	/**
	 * 根据手机号查询用户
	 * @param mobile
	 * @return 用户
	 */
	SysUser findByMobile(String mobile);

	/**
	 * 根据昵称模糊查询用户
	 * @param nickname
	 * @return 用户列表
	 */
	List<SysUser> findByNickname(String nickname);

	/**
	 * 根据昵称模糊查询用户
	 * @param nickname
	 * @return 用户列表
	 */
	List<SysUser> findByNicknameLike(String nickname);
	
	/**
	 * 根据用户名模糊查询用户
	 * @param username
	 * @return 用户列表
	 */
	List<SysUser> findByUsernameLike(String username);
}
