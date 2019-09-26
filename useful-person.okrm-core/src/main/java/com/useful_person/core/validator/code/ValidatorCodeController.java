package com.useful_person.core.validator.code;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.useful_person.core.properties.ImageCodeProperties;
import com.useful_person.core.properties.SecurityProperties;
import com.useful_person.core.properties.SmsCodeProperties;
import com.useful_person.core.validator.code.sms.SmsCodeSender;

import net.bytebuddy.utility.RandomString;

@RestController
public class ValidatorCodeController {

	public static final String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

	public static final String SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";

	@Autowired
	private SecurityProperties securityProperties;

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Autowired
	private ValidatorCodeGenerator imageCodeGenerator;

	@Autowired
	private SmsCodeSender smsCodeSender;

	@GetMapping("/code/captcha.jpg")
	public void getImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ImageCodeProperties imageCodeProperties = securityProperties.getCode().getImage();
		String randomStr = RandomString.make(imageCodeProperties.getLength());
		BufferedImage buferedImage = imageCodeGenerator.buildImageCode(new ServletWebRequest(request), randomStr);
		ImageCode imageCode = new ImageCode(randomStr, imageCodeProperties.getExpireIn());
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_IMAGE_CODE, imageCode);
		ImageIO.write(buferedImage, "jpeg", response.getOutputStream());
	}
	@GetMapping("/code/sms")
	public Map<String, Object> createSmsCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
		SmsCode smsCodeInSession = (SmsCode) sessionStrategy.getAttribute(new ServletWebRequest(request), SESSION_KEY_SMS_CODE);
		Map<String, Object> result = new HashMap<String, Object>();
		SmsCodeProperties smsCodeProperties = securityProperties.getCode().getSms();
		int expireIn = smsCodeProperties.getExpireIn();
		if (smsCodeInSession == null || smsCodeInSession.isExpired()) {
			String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
			int smsCodeLength = smsCodeProperties.getLength();
			int origin = Integer.valueOf("1".repeat(smsCodeLength));
			int bound = Integer.valueOf("9".repeat(smsCodeLength));
			String randomCode = String.valueOf(ThreadLocalRandom.current().nextInt(origin, bound));
			SmsCode smsCode = new SmsCode(randomCode, expireIn);
			smsCodeSender.send(mobile, smsCode.getCode());
			sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_SMS_CODE, smsCode);
			result.put("msg", "短信验证码发送成功，请在" + (expireIn / 60) + "分钟内使用。");
		} else {
			result.put("msg", "短信验证码未过期");
			LocalDateTime expireTime = smsCodeInSession.getExpireTime();
			LocalDateTime now = LocalDateTime.now();
			Duration duration = Duration.between(now, expireTime);
			result.put("expireIn", duration.toSeconds());
		}
		return result;
	}
}
