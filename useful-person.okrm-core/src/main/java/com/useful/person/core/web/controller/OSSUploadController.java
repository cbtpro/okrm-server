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

import com.useful.person.core.constants.ReturnCode;
import com.useful.person.core.domain.TempFile;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.properties.AppConstants;
import com.useful.person.core.services.TempFileService;
import com.useful.person.core.services.UserInfoService;
import com.useful.person.core.vo.ResponseData;

import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/upload")
public class OSSUploadController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private TempFileService tempFileService;

	@ApiOperation("前台将头像进行编辑后直接上传头像")
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
					break;
				}
				return returnMap;
			}
		};
		return callable;
	}

	/**
	 * 将图片上传到OSS，并标记为为临时，当用户点击保存后，头像地址才更新到用户，否则24小时后删除该文件
	 * @param user
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation("前台直接将图片上传到OSS")
	@PostMapping("/image")
	public Callable<ResponseData<String>> uploadImageToOSS(Authentication user, @RequestParam(required = true, name = "avatar") MultipartFile file, MultipartHttpServletRequest request, HttpServletResponse response) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		Callable<ResponseData<String>> callable = new Callable<>() {
			@Override
			public ResponseData<String> call() throws Exception {
				TempFile tempFile = null;
				for (Iterator<String> iterator = request.getFileNames(); iterator.hasNext();) {
					String imageName = iterator.next();
					MultipartFile mpf = request.getFile(imageName);
					tempFile = tempFileService.uploadFile(currentUser, mpf, true);
					break;
				}
				ResponseData<String> responseData = null;
				if (null != tempFile) {
					responseData = new ResponseData<String>(ReturnCode.CORRECT.getCode(), "上传成功，请点击保存才能生效哦！", tempFile.getUrl());
				} else {
					responseData = new ResponseData<String>(ReturnCode.ERROR.getCode(), "上传失败！", null);
				}
				return responseData;
			}
		};
		return callable;
	}
}
