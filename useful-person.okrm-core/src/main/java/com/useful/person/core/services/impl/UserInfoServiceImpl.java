/**
 * 用户service相关实现类
 */
package com.useful.person.core.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.useful.person.core.domain.UserInfoLog;
import com.useful.person.core.repository.UserInfoLogRepository;
import com.useful.person.core.repository.UserInfoRepository;
import com.useful.person.core.services.UserInfoService;

/**
 * @author peter
 *
 */
@Service(value = "userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private UserInfoLogRepository userInfoLogRepository;

	@Override
	@Transactional
	public void updateAvatarImage(String avatar, String userUuid, UserInfoLog userInfoLog) {
		userInfoRepository.updateAvatarImage(avatar, userUuid);
		userInfoLogRepository.save(userInfoLog);
	}

	@Override
	@Transactional
	public void updateUsername(String username, String userUuid, UserInfoLog userInfoLog) {
		userInfoRepository.updateUsername(username, userUuid);
		userInfoLogRepository.save(userInfoLog);
	}

	@Override
	@Transactional
	public void updatePassword(String password, String userUuid, UserInfoLog userInfoLog) {
		userInfoRepository.updatePassword(password, userUuid);
		userInfoLogRepository.save(userInfoLog);
	}

	@Override
	@Transactional
	public void updateNickname(String nickname, String userUuid, UserInfoLog userInfoLog) {
		userInfoRepository.updateNickname(nickname, userUuid);
		userInfoLogRepository.save(userInfoLog);
	}

	@Override
	@Transactional
	public void updateMobile(String mobile, String userUuid, UserInfoLog userInfoLog) {
		userInfoRepository.updateMobile(mobile, userUuid);
		userInfoLogRepository.save(userInfoLog);
	}

	@Override
	@Transactional
	public void updateBirthday(Date birthday, String userUuid, UserInfoLog userInfoLog) {
		userInfoRepository.updateBirthday(birthday, userUuid);
		userInfoLogRepository.save(userInfoLog);
	}

}
