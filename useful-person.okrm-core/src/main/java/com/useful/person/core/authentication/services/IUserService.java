package com.useful.person.core.authentication.services;

import java.util.Date;
import java.util.List;

import com.useful.person.core.domain.UserInfo;

/**
 * @author peter
 *
 */
public interface IUserService {

	/**
	 * 通过用户名注册用户
	 * @param userInfo 用户信息
	 * @return 用户信息
	 */
	UserInfo register(UserInfo userInfo);

	/**
	 * 通过手机号注册用户
	 * @param userInfo 用户信息
	 * @return 用户信息
	 */
	UserInfo registerByMobile(UserInfo userInfo);

	/**
	 * 删除用户
	 * @param userInfo 用户信息
	 * @return boolean 是否删除成功
	 */
	boolean delete(UserInfo userInfo);
	
	/**
	 * 根据uuid查找用户
	 * @param uuid 用户uuid
	 * @return UserInfo 用户信息
	 */
	UserInfo findByUuid(String uuid);
	
	/**
	 * 判断用户是否已经被注册
	 * @param username 用户名
	 * @return boolean 用户名是否已存在
	 */
	boolean isExistUsername(String username);

	/**
	 * 判断手机号是否已经注册
	 * @param mobile 手机号
	 * @return 是否已经注册
	 */
	boolean isExistMobile(String mobile);
	
	/**
	 * 根据用户名查找用户
	 * @param username 用户名
	 * @return 用户信息
	 */
	UserInfo findByUsername(String username);

	/**
	 * 根据昵称模糊查找用户
	 * @param nickname 用户昵称
	 * @return 用户列表
	 */
	List<UserInfo> findByNickname(String nickname);

	/**
	 * 根据手机号查找用户
	 * @param mobile 用户手机号
	 * @return 用户信息
	 */
	UserInfo findByMobile(String mobile);

	/**
	 * 更新用户
	 * @param uuid 用户uuid
	 * @param userInfo 用户信息
	 * @return 更新后的用户信息
	 */
	UserInfo updateUserInfo(UserInfo userInfo);

	/**
	 * 更改用户名
	 * @param uuid 用户uuid
	 * @param username 用户名
	 */
	void updateUsernameByUuid(String uuid, String username);

	/**
	 * 更改用户昵称
	 * @param uuid 用户uuid
	 * @param nickname 用户昵称
	 */
	void updateNicknameByUuid(String uuid, String nickname);

	/**
	 * 更改用户手机号
	 * @param uuid 用户uuid
	 * @param mobile 用户手机号
	 */
	void updateMobileByUuid(String uuid, String mobile);

	/**
	 * 更改用户邮箱
	 * @param uuid 用户uuid
	 * @param email 邮箱地址
	 */
	void updateEmailByUuid(String uuid, String email);

	/**
	 * 更改用户生日
	 * @param uuid 用户uuid
	 * @param birthday 生日
	 */
	void updateBirthdayByUuid(String uuid, Date birthday);

	/**
	 * 解绑旧的手机号码
	 * @param uuid
	 * @param mobile
	 */
	void unbindOldMobile(String uuid, String mobile);

	/**
	 * 解绑旧的邮箱地址
	 * @param uuid
	 * @param email
	 */
	void unbindOldEmail(String uuid, String email);

}
