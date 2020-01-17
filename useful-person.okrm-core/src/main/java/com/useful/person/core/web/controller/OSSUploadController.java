/**
 * 
 */
package com.useful.person.core.web.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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

import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.properties.AppConstants;
import com.useful.person.core.services.UserInfoService;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/upload")
public class OSSUploadController {

	@Autowired
	private UserInfoService userInfoService;

	@PostMapping("/avatar")
	public Callable<Map<String, String>> uploadImage(Authentication user, @RequestParam MultipartFile file,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		Callable<Map<String, String>> callable = new Callable<Map<String, String>>() {
			@Override
			public Map<String, String> call() throws Exception {
				UserInfo currentUser = (UserInfo) user.getPrincipal();
				Map<String, String> returnMap = new HashMap<>();
				for (Iterator<String> iterator = request.getFileNames(); iterator.hasNext();) {
					String imageName = iterator.next();
					MultipartFile mpf = request.getFile(imageName);
					userInfoService.updateAvatarImage(mpf, currentUser);
					returnMap.put(AppConstants.DEFAULT_RETURN_MESSAGE, "头像上传成功！");
				}
				return returnMap;
			}
		};
		return callable;
	}
}
