/**
 * 
 */
package com.useful.person.core.web.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.useful.person.core.constants.UserAction;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.domain.UserInfoLog;
import com.useful.person.core.oss.IUploadFile;
import com.useful.person.core.properties.AppConstants;
import com.useful.person.core.properties.AppProperties;
import com.useful.person.core.properties.OSSConfig;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.services.UserInfoService;
import com.useful.person.core.utils.FileUtil;

import net.coobird.thumbnailator.Thumbnails;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/upload")
public class OSSUploadController {

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private IUploadFile ossUploadFile;

	@Autowired
	private UserInfoService userInfoService;

	@PostMapping("/avatar")
	public Callable<Map<String, String>> uploadImage(Authentication user, @RequestParam MultipartFile file,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		Callable<Map<String, String>> callable = new Callable<Map<String, String>>() {
			@Override
			public Map<String, String> call() throws Exception {
				OSSConfig ossConfig = securityProperties.getOss().getConfig();
				UserInfo currentUser = (UserInfo) user.getPrincipal();
				String userUuid = currentUser.getUuid();
				Map<String, String> returnMap = new HashMap<>();
				for (Iterator<String> iterator = request.getFileNames(); iterator.hasNext();) {
					String imageName = iterator.next();
					MultipartFile mpf = request.getFile(imageName);
					UUID uuid = UUID.randomUUID();
					String fileName = uuid.toString();
					String originalfileName = mpf.getOriginalFilename();
					String avatarExtension = originalfileName.substring(originalfileName.lastIndexOf(".") + 1, originalfileName.length());
					String tempDir = appProperties.getFile().getTemp().getDir();
					String avatarName = fileName + "." + avatarExtension;
					String outFile = tempDir + "/" + avatarName;
					Thumbnails.of(file.getInputStream()).size(480, 480).outputFormat(avatarExtension).toFile(outFile);;
					String path = ossConfig.getAvatarDir() + "/" + avatarName;
//					try {
						ossUploadFile.uploadToOSS(outFile, path);
						String avatarUrl = ossConfig.getResourceUrl() + "/" + path;
						UserInfoLog userInfoLog = UserInfoLog.builder().actionType(UserAction.UPDATE_AVATAR).actionValue(avatarUrl).oldValue(currentUser.getAvatar()).user(currentUser).build();
						userInfoService.updateAvatarImage(avatarUrl, userUuid, userInfoLog);
						returnMap.put(AppConstants.DEFAULT_RETURN_MESSAGE, "头像上传成功！");
//					} catch (IOException e) {
//						returnMap.put(AppConstants.DEFAULT_RETURN_MESSAGE, "头像上传失败！");
//					} finally {
						FileUtil.deleteFile(outFile);
//					}
				}
				return returnMap;
			}
		};
		return callable;
	}
}
