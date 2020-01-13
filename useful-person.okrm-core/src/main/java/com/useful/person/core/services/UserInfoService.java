/**
 * 用户信息相关service
 */
package com.useful.person.core.services;

import java.util.Date;

import com.useful.person.core.domain.UserInfoLog;

/**
 * @author peter
 *
 */
public interface UserInfoService {

	/**
	 * 更新头像信息
	 * @param avatar
	 * @param userUuid
	 * @param userInfoLog
	 */
	void updateAvatarImage(String avatar, String userUuid, UserInfoLog userInfoLog);
	

	/**
	 * 更新用户名
	 * @param username
	 * @param userUuid
	 * @param userInfoLog
	 */
	void updateUsername(String username, String userUuid, UserInfoLog userInfoLog);

	/**
	 * 更新用户密码
	 * @param password
	 * @param userUuid
	 * @param userInfoLog
	 */
	void updatePassword(String password, String userUuid, UserInfoLog userInfoLog);

	/**
	 * 更新用户昵称
	 * @param nickname
	 * @param userUuid
	 * @param userInfoLog
	 */
	void updateNickname(String nickname, String userUuid, UserInfoLog userInfoLog);

	/**
	 * 更新用户手机号
	 * @param mobile
	 * @param userUuid
	 * @param userInfoLog
	 */
	void updateMobile(String mobile, String userUuid, UserInfoLog userInfoLog);

	/**
	 * 更新用户生日
	 * @param birthday
	 * @param userUuid
	 * @param userInfoLog
	 */
	void updateBirthday(Date birthday, String userUuid, UserInfoLog userInfoLog);
}
