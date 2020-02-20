package com.useful.person.core.authentication.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.useful.person.core.authentication.exception.EmailExistException;
import com.useful.person.core.authentication.exception.GeneralException;
import com.useful.person.core.authentication.exception.MobileExistException;
import com.useful.person.core.authentication.exception.UserNotExistException;
import com.useful.person.core.authentication.exception.UsernameExistException;
import com.useful.person.core.authentication.repository.UserRepository;
import com.useful.person.core.authentication.services.IUserService;
import com.useful.person.core.constants.UserAction;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.domain.UserInfoLog;
import com.useful.person.core.repository.UserInfoLogRepository;
import com.useful.person.core.repository.UserInfoRepository;

/**
 * 
 * @author peter
 *
 */
@Service("userServices")
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	private UserInfoLogRepository userInfoLogRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public UserInfo register(UserInfo userInfo) {
		String username = userInfo.getUsername();
		boolean isExistUsername = isExistUsername(username);
		if (isExistUsername) {
			throw new UsernameExistException(username);
		}
		encryptPassword(userInfo);
		UserInfo newUserInfo = userRepository.save(userInfo);
		UserInfoLog userInfoLog = UserInfoLog.builder().actionType(UserAction.SIGNUP).user(newUserInfo).build();
		userInfoLogRepository.save(userInfoLog);
		return newUserInfo;
	}

	@Override
	@Transactional
	public UserInfo registerByMobile(UserInfo userInfo) {
		String mobile = userInfo.getMobile();
		boolean isExistMobile = isExistMobile(mobile);
		if (isExistMobile) {
			throw new MobileExistException(mobile);
		}
		encryptPassword(userInfo);
		UserInfo newUserInfo = userRepository.save(userInfo);
		UserInfoLog userInfoLog = UserInfoLog.builder().actionType(UserAction.SIGNUP).user(newUserInfo).build();
		userInfoLogRepository.save(userInfoLog);
		return newUserInfo;
	}

	@Override
	@Transactional
	public boolean delete(UserInfo userInfo) {
		userRepository.delete(userInfo);
		return true;
	}

	@Override
	public UserInfo findByUuid(String uuid) {
		return userRepository.findById(uuid).orElseThrow(() -> new UserNotExistException(uuid));
	}

	@Override
	public UserInfo findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<UserInfo> findByNickname(String nickname) {
		return userRepository.findByNicknameLike(nickname);
	}

	@Override
	public boolean isExistUsername(String username) {
		UserInfo userInfo = userRepository.findByUsername(username);
		return userInfo != null;
	}

	@Override
	public boolean isExistMobile(String mobile) {
		UserInfo userInfo = userRepository.findByMobile(mobile);
		return userInfo != null;
	}

	private void encryptPassword(UserInfo user) {
		String password = user.getPassword();
		password = passwordEncoder.encode(password);
		user.setPassword(password);
	}

	@Override
	public UserInfo findByMobile(String mobile) {
		return userRepository.findByMobile(mobile);
	}

	@Override
	@Transactional
	public UserInfo updateUserInfo(UserInfo userInfo) {
		return userRepository.save(userInfo);
	}

	@Override
	@Transactional
	public void updateUsernameByUuid(String uuid, String username) {
		UserInfo userInfo = userRepository.findByUsername(username);
		if (userInfo == null) {
			userInfoRepository.updateUsername(username, uuid);
		} else {
			throw new UsernameExistException(username);
		}
	}

	@Override
	@Transactional
	public void updateNicknameByUuid(String uuid, String nickname) {
		userInfoRepository.updateNickname(nickname, uuid);
	}

	@Override
	@Transactional
	public void updateMobileByUuid(String uuid, String mobile) {
		UserInfo userInfo = userRepository.findByUuidNotAndMobile(uuid, mobile);
		if (userInfo == null) {
			userInfoRepository.updateMobile(mobile, uuid);
			UserInfoLog userInfoLog = UserInfoLog.builder().user(userInfo).actionType(UserAction.UPDATE_MOBILE).oldValue(null).actionValue(mobile).build();
			userInfoLogRepository.save(userInfoLog);
		} else {
			throw new MobileExistException(mobile);
		}
	}

	@Override
	@Transactional
	public void updateEmailByUuid(String uuid, String email) {
		UserInfo userInfo = userRepository.findByEmail(email);
		if (userInfo == null) {
			userInfoRepository.updateEmail(email, uuid);
		} else {
			throw new EmailExistException(email);
		}
	}

	@Override
	@Transactional
	public void updateBirthdayByUuid(String uuid, Date birthday) {
		userInfoRepository.updateBirthday(birthday, uuid);
	}

	@Override
	@Transactional
	public void unbindOldMobile(String uuid, String mobile) {
		UserInfo userInfo = userRepository.findById(uuid).orElseThrow(() -> new GeneralException("", "手机号解绑失败"));
		if (userInfo != null && uuid.equalsIgnoreCase(userInfo.getUuid()) && mobile.equals(userInfo.getMobile())) {
			// 解绑手机
			userInfoRepository.updateMobile(null, uuid);
			UserInfoLog userInfoLog = UserInfoLog.builder().user(userInfo).actionType(UserAction.UPDATE_MOBILE).actionValue(null).oldValue(mobile).build();
			userInfoLogRepository.save(userInfoLog);
		} else {
			throw new GeneralException(userInfo.getMobile(), "绑定的手机号不正确");
		}
	}

}
