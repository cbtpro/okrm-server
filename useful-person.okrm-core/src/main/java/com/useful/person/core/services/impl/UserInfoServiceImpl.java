/**
 * 用户service相关实现类
 */
package com.useful.person.core.services.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.useful.person.core.constants.UserAction;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.domain.UserInfoLog;
import com.useful.person.core.exception.OSSException;
import com.useful.person.core.oss.IUploadFile;
import com.useful.person.core.properties.AppProperties;
import com.useful.person.core.properties.OSSConfig;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.repository.UserInfoLogRepository;
import com.useful.person.core.repository.UserInfoRepository;
import com.useful.person.core.services.UserInfoService;
import com.useful.person.core.utils.FileUtil;

import net.coobird.thumbnailator.Thumbnails;

/**
 * @author peter
 *
 */
@Service(value = "userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private IUploadFile ossUploadFile;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private UserInfoLogRepository userInfoLogRepository;

	@Override
	@Transactional
	public void updateAvatarImage(MultipartFile multipartFile, UserInfo currentUser) {
		String userUuid = currentUser.getUuid();
		OSSConfig ossConfig = securityProperties.getOss().getConfig();
		String fileName = UUID.randomUUID().toString();
		String originalfileName = multipartFile.getOriginalFilename();
		String avatarExtension = originalfileName.substring(originalfileName.lastIndexOf(".") + 1,
				originalfileName.length());
		String tempDir = appProperties.getFile().getTemp().getDir();
		String avatarName = fileName + "." + avatarExtension;
		String outFile = tempDir + "/" + avatarName;
		try {
			Thumbnails.of(multipartFile.getInputStream()).size(480, 480).outputFormat(avatarExtension).toFile(outFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new OSSException("上传头像失败！");
		}
		String path = ossConfig.getAvatarDir() + "/" + avatarName;
		String avatarUrl = ossConfig.getResourceUrl() + "/" + path;
		UserInfoLog userInfoLog = UserInfoLog.builder().actionType(UserAction.UPDATE_AVATAR)
				.actionValue(avatarUrl).oldValue(currentUser.getAvatar()).user(currentUser).build();
		ossUploadFile.uploadToOSS(outFile, path);
		userInfoRepository.updateAvatarImage(avatarUrl, userUuid);
		userInfoLogRepository.save(userInfoLog);
		FileUtil.deleteFile(outFile);
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
	public void updateBirthday(Timestamp birthday, String userUuid, UserInfoLog userInfoLog) {
		userInfoRepository.updateBirthday(birthday, userUuid);
		userInfoLogRepository.save(userInfoLog);
	}

}
