package com.useful.person.core.authentication.services.impl;

import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.IdenticonAvatar;
import com.useful.person.core.authentication.exception.EmailExistException;
import com.useful.person.core.authentication.exception.GeneralException;
import com.useful.person.core.authentication.exception.MobileExistException;
import com.useful.person.core.authentication.exception.UserNotExistException;
import com.useful.person.core.authentication.exception.UsernameExistException;
import com.useful.person.core.authentication.repository.UserRepository;
import com.useful.person.core.authentication.services.IUserService;
import com.useful.person.core.constants.UserAction;
import com.useful.person.core.constants.UserRole;
import com.useful.person.core.domain.Role;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.domain.UserInfoLog;
import com.useful.person.core.repository.RoleRepository;
import com.useful.person.core.repository.UserInfoLogRepository;
import com.useful.person.core.repository.UserInfoRepository;
import com.useful.person.core.services.UserInfoService;
import com.useful.person.core.vo.Address;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author peter
 *
 */
@Slf4j
@Service("userServices")
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	private UserInfoLogRepository userInfoLogRepository;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private RoleRepository roleRepository;

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
		Role role = roleRepository.findByRolename(UserRole.NORMAL.getName());
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		userInfo.setRoles(roles);
		UserInfo newUserInfo = userRepository.save(userInfo);
		UserInfoLog userInfoLog = UserInfoLog.builder().actionType(UserAction.SIGNUP).user(newUserInfo).build();
		userInfoLogRepository.save(userInfoLog);

		String threadName = userInfo.getUuid() + ", " + userInfo.getUsername() + ", " + userInfo.getNickname();
		String description = threadName + "：生成头像！";
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Avatar avatar = IdenticonAvatar.newAvatarBuilder().size(512, 512).build();
					BufferedImage avatarBufferedImage = avatar.create(System.currentTimeMillis());
					userInfoService.updateAvatarImage(avatarBufferedImage, userInfo);
					log.info(description);
				} catch (Exception e) {
					log.info(threadName + "：头像生成失败！");
				}
			}
		});
		thread.setName(threadName);
		thread.start();
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
		Role role = roleRepository.findByRolename(UserRole.NORMAL.getName());
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		userInfo.setRoles(roles);
		UserInfo newUserInfo = userRepository.save(userInfo);
		UserInfoLog userInfoLog = UserInfoLog.builder().actionType(UserAction.SIGNUP).user(newUserInfo).build();
		userInfoLogRepository.save(userInfoLog);

		String threadName = userInfo.getUuid() + ", " + userInfo.getUsername() + ", " + userInfo.getNickname();
		String description = threadName + "：生成头像！";
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Avatar avatar = IdenticonAvatar.newAvatarBuilder().size(512, 512).build();
					BufferedImage avatarBufferedImage = avatar.create(System.currentTimeMillis());
					userInfoService.updateAvatarImage(avatarBufferedImage, newUserInfo);
					log.info(description);
				} catch (Exception e) {
					log.info(threadName + "：头像生成失败！");
				}}
		});
		thread.setName(threadName);
		thread.start();
		return newUserInfo;
	}

	@Transactional
	@Override
	public void updateUserPassword(String uuid, String oldPassword, String newPassword) {
		UserInfo userInfo = userRepository.findById(uuid).orElseThrow(() -> new UserNotExistException(uuid));
		if (passwordEncoder.matches(oldPassword, userInfo.getPassword())) {
			String enPassowrd = passwordEncoder.encode(newPassword);
			int count = userInfoRepository.updateUserPassword(enPassowrd, uuid);
			if (count == 0) {
				throw new GeneralException("", "密码修改失败！");
			}
			UserInfoLog userInfoLog = UserInfoLog.builder().actionType(UserAction.UPDATE_PASSWORD).user(userInfo).build();
			userInfoLogRepository.save(userInfoLog);
		} else {
			throw new GeneralException("", "旧密码错误！");
		}
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
			UserInfo currentUser = UserInfo.builder().uuid(uuid).build();
			UserInfoLog userInfoLog = UserInfoLog.builder().user(currentUser).actionType(UserAction.UPDATE_MOBILE).oldValue(null).actionValue(mobile).build();
			userInfoLogRepository.save(userInfoLog);
		} else {
			throw new MobileExistException(mobile);
		}
	}

	@Override
	@Transactional
	public void updateEmailByUuid(String uuid, String email) {
		// 查询除自身外是手机号是否已被使用
		UserInfo userInfo = userRepository.findByUuidNotAndEmail(uuid, email);
		if (userInfo == null) {
			userInfoRepository.updateEmail(email, uuid);
			UserInfo currentUser = UserInfo.builder().uuid(uuid).build();
			UserInfoLog userInfoLog = UserInfoLog.builder().user(currentUser).actionType(UserAction.UPDATE_EMAIL).oldValue(null).actionValue(email).build();
			userInfoLogRepository.save(userInfoLog);
		} else {
			throw new EmailExistException(email);
		}
	}

	@Override
	@Transactional
	public void updateBirthdayByUuid(String uuid, Timestamp birthday) {
		userInfoRepository.updateBirthday(birthday, uuid);
	}

	@Override
	@Transactional
	public void unbindOldMobile(String uuid, String mobile) {
		UserInfo userInfo = userRepository.findById(uuid).orElseThrow(() -> new GeneralException(mobile, "手机号解绑失败"));
		if (userInfo != null && uuid.equalsIgnoreCase(userInfo.getUuid()) && mobile.equals(userInfo.getMobile())) {
			// 解绑手机
			userInfoRepository.updateMobile(null, uuid);
			UserInfoLog userInfoLog = UserInfoLog.builder().user(userInfo).actionType(UserAction.UPDATE_MOBILE).actionValue(null).oldValue(mobile).build();
			userInfoLogRepository.save(userInfoLog);
		} else {
			throw new GeneralException(mobile, "绑定的手机号不正确");
		}
	}

	@Override
	@Transactional
	public void unbindOldEmail(String uuid, String email) {
		UserInfo userInfo = userRepository.findById(uuid).orElseThrow(() -> new GeneralException(email, "邮箱解绑失败"));
		if (userInfo != null && uuid.equalsIgnoreCase(userInfo.getUuid()) && email.equals(userInfo.getEmail())) {
			// 解绑邮箱
			userInfoRepository.updateEmail(null, uuid);
			UserInfoLog userInfoLog = UserInfoLog.builder().user(userInfo).actionType(UserAction.UPDATE_EMAIL).actionValue(null).oldValue(email).build();
			userInfoLogRepository.save(userInfoLog);
		} else {
			throw new GeneralException(email, "绑定的邮箱不正确");
		}
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT)
	public void updateRealname(String uuid, String identityCardName, String identityCardNo) {
		UserInfo userInfo = userRepository.findById(uuid).orElseThrow(() -> new GeneralException(null, "实名制失败"));
		userInfoRepository.updateIdentityCard(identityCardName, identityCardNo, uuid);
		UserInfoLog userInfoLog = UserInfoLog.builder().user(userInfo).actionType(UserAction.UPDATE_IDENTITY_CARD).oldValue(null).actionValue(userInfo.getIdentityCardName() + "," + userInfo.getIdentityCardNo()).build();
		userInfoLogRepository.save(userInfoLog);
	}
	@Override
	public Address getUserAddress(String uuid) {
		return userInfoRepository.findAddressByUuid(uuid);
	}

	@Override
	@Transactional
	public int updateAddressByUuid(String uuid, Address address) {
		return userInfoRepository.updateAddress(address.getLongitude(), address.getLatitude(), uuid);
	}

	@Override
	public List<Address> getUserNearbyUserAddress(String uuid, Double longitude0, Double latitude0, Double longitude1, Double latitude1, Double longitude, Double latitude) {
		Double minLongitude = longitude0;
		Double maxLongitude = longitude1;
		Double minLatitude = latitude0;
		Double maxLatitude = latitude1;
		if (minLatitude > maxLatitude) {
			Double temp = minLatitude;
			minLatitude = maxLatitude;
			maxLatitude = temp;
		}
		/**
		 * 判断是否需要拆分区域
		 * 经度170 -> 180 -> (-170)需要拆分成 170 -> 180/ (-180) -> (-170)
		 * 纬度在高德、腾讯等地图上不会出现跨区间，不需要拆分。但是如果前端采用了3D地球，需要另外考虑方案。
		 */
		if (minLongitude > maxLongitude) {
			// 第一个区间
			Double range0MinLongitude = longitude0;
			Double range0MaxLongitude = 180d;
			// 第二个区间
			Double range1MinLongitude = -180d;
			Double range1MaxLongitude = longitude1;
			return userInfoRepository.queryUserNearByUserAddressRange(range0MinLongitude, range0MaxLongitude, range1MinLongitude, range1MaxLongitude, minLatitude, maxLatitude);
		}
		return userInfoRepository.queryUserNearbyUserAddress(minLongitude, maxLongitude, minLatitude, maxLatitude);
	}

	@Override
	@Transactional
	public UserInfo updateUserInfo(String uuid, String avatar, String username, String nickname, String province, String city, String county, Timestamp birthday) {
		UserInfo userInfo = userInfoRepository.findById(uuid).orElseThrow(() -> new GeneralException("", "更新信息失败！"));
		int count = userInfoRepository.updateUserInfo(uuid, avatar, username, nickname, province, city, county, birthday);
		if (count > 0) {
			UserInfoLog.builder().actionType(UserAction.UPDATE_AVATAR).actionValue(avatar).oldValue(userInfo.getAvatar()).build();
			UserInfoLog.builder().actionType(UserAction.UPDATE_USERNAME).actionValue(username).oldValue(userInfo.getUsername()).build();
			UserInfoLog.builder().actionType(UserAction.UPDATE_NICKNAME).actionValue(nickname).oldValue(userInfo.getNickname()).build();
			Timestamp oldBirthday = userInfo.getBirthday();
			String oldBirthDayStr = oldBirthday != null ? oldBirthday.toString() : null;
			UserInfoLog.builder().actionType(UserAction.UPDATE_BIRTHDAY).actionValue(birthday.toString()).oldValue(oldBirthDayStr).build();
			return userInfoRepository.findById(uuid).orElseThrow(() -> new GeneralException("", "更新信息失败！"));
		}
		throw new GeneralException("", "更新信息失败");
	}

}
