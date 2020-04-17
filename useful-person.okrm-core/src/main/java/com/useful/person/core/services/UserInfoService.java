/**
 * 用户信息相关service
 */
package com.useful.person.core.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.useful.person.core.domain.UserInfo;
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
	String updateAvatarImage(MultipartFile multipartFile, UserInfo curentUser);
	

	/**
	 * 更新用户名
	 * @param username
	 * @param userUuid
	 * @param userInfoLog
	 */
	void updateUsername(String username, String userUuid, UserInfoLog userInfoLog);

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
	void updateBirthday(Timestamp birthday, String userUuid, UserInfoLog userInfoLog);

	List<UserInfo> queryUsers();
}
