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
import javax.persistence.criteria.JoinType;
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

import com.useful.person.core.authentication.exception.UserNotExistException;
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
import com.useful.person.core.services.RoleService;
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

	@Autowired
	private RoleService roleService;

	@Override
	@Transactional(rollbackFor = IOException.class)
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
	@Transactional(rollbackFor = Exception.class)
	public void updateUsername(String username, String userUuid, UserInfoLog userInfoLog) {
		userInfoRepository.updateUsername(username, userUuid);
		userInfoLogRepository.save(userInfoLog);
	}

	@Override
	@Transactional(rollbackFor = IOException.class)
	public void updateNickname(String nickname, String userUuid, UserInfoLog userInfoLog) {
		userInfoRepository.updateNickname(nickname, userUuid);
		userInfoLogRepository.save(userInfoLog);
	}

	@Override
	@Transactional(rollbackFor = IOException.class)
	public void updateMobile(String mobile, String userUuid, UserInfoLog userInfoLog) {
		userInfoRepository.updateMobile(mobile, userUuid);
		userInfoLogRepository.save(userInfoLog);
	}

	@Override
	@Transactional(rollbackFor = IOException.class)
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
			String email, Date startTime, Date endTime, Boolean enabled, String[] rolenames) {

		List<Role> roleList = roleService.findByRolenames(rolenames);
		Specification<UserInfo> specification = buildSpecification(username, nickname, mobile, email, startTime, endTime, enabled, roleList);
		return userInfoRepository.findAll(specification, pageable);
	}

	@Override
	public Page<UserInfo> queryUsersHasAdminPage(Pageable pageable) {
		List<Role> roleList = new ArrayList<>();
		Role adminRole = roleService.findByRolename(UserRole.ADMIN.getName());
		if (adminRole != null) {
			roleList.add(adminRole);
		}
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
					Join<UserInfo, Role> join = root.<UserInfo, Role>join("roles", JoinType.LEFT);
					predicates.add(join.in(roleList));
				}
				query.groupBy(root.get("uuid"));
				return cb.and(predicates.toArray(new Predicate[0])); // 使用and链接
			}
		};
		return specification;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addUsernamesToAdmin(List<String> usernames) {
		List<UserInfo> users = userInfoRepository.findByUsernames(usernames);
		Role adminRole = roleService.findByRolename(UserRole.ADMIN.getName());
		StringBuilder msg = new StringBuilder();
		List<String> existAdminUsers = new ArrayList<>();
		List<String> successUsers = new ArrayList<>();
		for (int i = 0; i < users.size(); i++) {
			UserInfo user = users.get(i);
			if (!user.hasRole(adminRole)) {
				user.addRole(adminRole);
				userInfoRepository.save(user);
				successUsers.add(user.getUsername());
			} else {
				existAdminUsers.add(user.getUsername());
			}
		}
		if (!existAdminUsers.isEmpty()) {
			msg.append(StringUtils.join(existAdminUsers, ", ") + " 已拥ADMIN权限！");
		}
		if (!successUsers.isEmpty()) {
			msg.append(StringUtils.join(successUsers, ", ") + " 成功添加ADMIN权限！");
		}
		return msg.toString();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String removeUserFromAdmin(String uuid) {
		UserInfo user = userInfoRepository.findById(uuid).orElseThrow(() -> new UserNotExistException(uuid));
		Role adminRole = roleService.findByRolename(UserRole.ADMIN.getName());
		StringBuilder msg = new StringBuilder();
		if (user.hasRole(adminRole)) {
			user.removeRole(adminRole);
			msg.append(user.getUsername() + "已移除Admin角色！");
		} else {
			msg.append(user.getUsername() + "没有Admin角色！");
		}
		return msg.toString();
	}
}
