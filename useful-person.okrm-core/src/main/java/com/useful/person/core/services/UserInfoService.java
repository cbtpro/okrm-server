/**
 * 用户信息相关service
 */
package com.useful.person.core.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	/**
	 * 查询所有用户信息
	 * @return
	 */
	List<UserInfo> queryUsers();

	/**
	 * 分页查询所有用户信息
	 * @param pageable
	 * @param username
	 * @param nickname
	 * @param mobile
	 * @param email
	 * @param startTime
	 * @param endTime
	 * @param enabled
	 * @return Page<UserInfo>
	 */
	Page<UserInfo> queryUsersPage(Pageable pageable, String username, String nickname, String mobile, String email, Date startTime, Date endTime, Boolean enabled);

	/**
	 * 分页查询所有用户信息
	 * @param pageable
	 * @param username
	 * @param nickname
	 * @param mobile
	 * @param email
	 * @param startTime
	 * @param endTime
	 * @param enabled
	 * @param roles
	 * @return Page<UserInfo>
	 */
	Page<UserInfo> queryUsersPage(Pageable pageable, String username, String nickname, String mobile, String email, Date startTime, Date endTime, Boolean enabled, String[] roles);

	Page<UserInfo> queryUsersHasAdminPage(Pageable pageable);

	String addUsernamesToAdmin(List<String> usernames);

	String removeUserFromAdmin(String uuid);
}
