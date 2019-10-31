package com.useful.person.core.authentication.services;

import java.util.List;

import com.useful.person.core.authentication.domain.UserInfo;

/**
 * @author peter
 *
 */
public interface IUserService {

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	UserInfo register(UserInfo user);

	/**
	 * 删除用户
	 * @param user
	 * @return boolean
	 */
	boolean delete(UserInfo user);
	
	/**
	 * 根据uuid查找用户
	 * @param uuid
	 * @return UserInfo
	 */
	UserInfo findByUuid(String uuid);
	
	/**
	 * 判断用户是否存在
	 * @param username
	 * @return boolean
	 */
	boolean isExistUsername(String username);
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	UserInfo findByUsername(String username);

	/**
	 * 根据昵称模糊查找用户
	 * @param nickname
	 * @return
	 */
	List<UserInfo> findByNickname(String nickname);

}
