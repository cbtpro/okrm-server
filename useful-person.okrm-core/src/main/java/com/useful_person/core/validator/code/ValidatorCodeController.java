package com.useful_person.core.validator.code;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.useful_person.core.properties.ImageCodeProperties;
import com.useful_person.core.properties.SecurityProperties;

import net.bytebuddy.utility.RandomString;

@RestController
public class ValidatorCodeController {

	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

	@Autowired
	private SecurityProperties securityProperties;

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Autowired
	private ValidatorCodeGenerator imageCodeGenerator;

	@GetMapping("/code/captcha.jpg")
	public void getImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ImageCodeProperties imageCodeProperties = securityProperties.getCode().getImage();
		String randomStr = RandomString.make(imageCodeProperties.getLength());
		BufferedImage buferedImage = imageCodeGenerator.buildImageCode(new ServletWebRequest(request), randomStr);
		ImageCode imageCode = new ImageCode(randomStr, imageCodeProperties.getExpireIn());
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
		ImageIO.write(buferedImage, "jpeg", response.getOutputStream());
	}

}
