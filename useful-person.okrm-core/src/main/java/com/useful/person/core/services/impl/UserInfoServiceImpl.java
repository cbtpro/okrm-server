/**
 * 用户service相关实现类
 */
package com.useful.person.core.services.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.useful.person.core.constants.UserAction;
import com.useful.person.core.constants.UserRole;
import com.useful.person.core.domain.Role;
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
	public String updateAvatarImage(MultipartFile multipartFile, UserInfo currentUser) {
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
		return avatarUrl;
	}

	@Override
	@Transactional
	public void updateUsername(String username, String userUuid, UserInfoLog userInfoLog) {
		userInfoRepository.updateUsername(username, userUuid);
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

	@Override
	public List<UserInfo> queryUsers() {
		return userInfoRepository.findAll();
	}

	@Override
	public Page<UserInfo> queryUsersPage(Pageable pageable, String username, String nickname, String mobile, String email, Date startTime, Date endTime, Boolean enabled) {
		Specification<UserInfo> specification = buildSpecification(username, nickname, mobile, email, startTime, endTime, enabled, null);
		return userInfoRepository.findAll(specification, pageable);
	}

	@Override
	public Page<UserInfo> queryUsersPage(Pageable pageable, String username, String nickname, String mobile,
			String email, Date startTime, Date endTime, Boolean enabled, String[] roles) {
		List<Role> roleList = new ArrayList<>();
		if (roles != null && roles.length > 0) {
			for (int i = 0; i < roles.length; i++) {
				roleList.add(Role.builder().rolename(roles[i]).build());
			}
		}
		Specification<UserInfo> specification = buildSpecification(username, nickname, mobile, email, startTime, endTime, enabled, roleList);
		return userInfoRepository.findAll(specification, pageable);
	}

	@Override
	public Page<UserInfo> queryUsersHasAdminPage(Pageable pageable) {
		List<Role> roleList = new ArrayList<>();
		roleList.add(Role.builder().rolename(UserRole.ADMIN.getName()).build());
		Specification<UserInfo> spec = buildSpecification(null, null, null, null, null, null, null, roleList);
		return userInfoRepository.findAll(spec, pageable);
	}

	private Specification<UserInfo> buildSpecification(String username, String nickname, String mobile, String email, Date startTime, Date endTime, Boolean enabled, List<Role> roleList) {
		Specification<UserInfo> specification = new Specification<>() {

			private static final long serialVersionUID = 2751395428912835260L;

			@Override
			public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (StringUtils.isNotBlank(username)) {
					Predicate equalsUsername = cb.equal(root.get("username").as(String.class), username);
					predicates.add(equalsUsername);
				}
				if (StringUtils.isNotBlank(nickname)) {
					Predicate likeNickname = cb.like(root.get("nickname").as(String.class), "%" + nickname + "%");
					predicates.add(likeNickname);
				}
				if (StringUtils.isNotBlank(mobile)) {
					Predicate equalsMobile = cb.equal(root.get("mobile").as(String.class), mobile);
					predicates.add(equalsMobile);
				}
				if (StringUtils.isNotBlank(email)) {
					Predicate equalsEmail = cb.equal(root.get("email").as(String.class), email);
					predicates.add(equalsEmail);
				}
				if (startTime != null && endTime != null) {
					Predicate betweenCreateTime = cb.between(root.<Timestamp>get("createTime"), startTime, endTime);
					predicates.add(betweenCreateTime);
				}
				if (startTime != null && endTime == null) {
					Predicate greaterThanCreateTime = cb.greaterThanOrEqualTo(root.<Timestamp>get("createTime"), startTime);
					predicates.add(greaterThanCreateTime);
				}
				if (startTime == null && endTime != null) {
					Predicate greaterThanCreateTime = cb.lessThanOrEqualTo(root.<Timestamp>get("createTime"), endTime);
					predicates.add(greaterThanCreateTime);
				}
				if (enabled != null) {
					Predicate equalsEnabled = cb.equal(root.get("enabled").as(Boolean.class), enabled.booleanValue());
					predicates.add(equalsEnabled);
				}
				int roleSize = roleList.size();
				if (roleList != null && roleSize > 0) {
					Join<UserInfo, Role> join = root.join("roles");
					predicates.add(join.get("rolename").in(UserRole.ADMIN.getName()));
				}
				return cb.and(predicates.toArray(new Predicate[0])); // 使用and链接
			}
		};
		return specification;
	}
}
