package com.useful.person.core.authentication.services;

import java.util.List;

import com.useful.person.core.domain.UserInfo;

/**
 * @author peter
 *
 */
public interface IUserService {

	/**
	 * 通过用户名注册用户
	 * @param userInfo
	 * @return
	 */
	UserInfo register(UserInfo userInfo);

	/**
	 * 通过手机号注册用户
	 * @param userInfo
	 * @return
	 */
	UserInfo registerByMobile(UserInfo userInfo);

	/**
	 * 删除用户
	 * @param userInfo
	 * @return boolean
	 */
	boolean delete(UserInfo userInfo);
	
	/**
	 * 根据uuid查找用户
	 * @param uuid
	 * @return UserInfo
	 */
	UserInfo findByUuid(String uuid);
	
	/**
	 * 判断用户是否已经被注册
	 * @param username
	 * @return boolean
	 */
	boolean isExistUsername(String username);

	/**
	 * 判断手机号是否已经注册
	 * @param mobile
	 * @return
	 */
	boolean isExistMobile(String mobile);
	
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

	/**
	 * 根据手机号查找用户
	 * @param mobile
	 * @return
	 */
	UserInfo findByMobile(String mobile);

	/**
	 * 更新用户
	 * @param uuid
	 * @param userInfo
	 * @return
	 */
	UserInfo updateUserInfo(UserInfo userInfo);

	void updateNicknameByUuid(String uuid, String nickname);
}
