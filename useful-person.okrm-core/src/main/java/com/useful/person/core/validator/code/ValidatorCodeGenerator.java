package com.useful.person.core.validator.code;

import java.awt.image.BufferedImage;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 
 * @author peter
 *
 */
public interface ValidatorCodeGenerator {

	/**
	 * 生成图片验证码
	 * @param request
	 * @param randomStr
	 * @return BufferedImage
	 */
	BufferedImage buildImageCode(ServletWebRequest request, String randomStr);

}
