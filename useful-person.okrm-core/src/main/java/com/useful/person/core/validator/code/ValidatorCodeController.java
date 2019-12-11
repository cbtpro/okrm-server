package com.useful.person.core.validator.code;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

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

import com.google.gson.Gson;
import com.useful.person.core.properties.ImageCodeProperties;
import com.useful.person.core.properties.AppConstants;
import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.properties.SmsCodeProperties;
import com.useful.person.core.redis.impl.SmsCodeRedisOperation;
import com.useful.person.core.validator.code.captcha.ImageCode;
import com.useful.person.core.validator.code.sms.SmsCode;
import com.useful.person.core.validator.code.sms.SmsCodeSender;

import net.bytebuddy.utility.RandomString;

/**
 * 
 * @author peter
 *
 */
@RestController
public class ValidatorCodeController {

	@Autowired
	private SecurityProperties securityProperties;

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Autowired
	private SmsCodeRedisOperation smsCodeRedisOperation;

	@Autowired
	private ValidatorCodeGenerator imageCodeGenerator;

	@Autowired
	private SmsCodeSender smsCodeSender;

	@GetMapping("/code/captcha.jpg")
	public Callable<String> getImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				ImageCodeProperties imageCodeProperties = securityProperties.getCode().getImage();
				String randomStr = RandomString.make(imageCodeProperties.getLength());
				BufferedImage buferedImage = imageCodeGenerator.buildImageCode(new ServletWebRequest(request), randomStr);
				ImageCode imageCode = new ImageCode(randomStr, imageCodeProperties.getExpireIn());
				sessionStrategy.setAttribute(new ServletWebRequest(request), SecurityConstants.DEFAULT_SESSION_KEY_IMAGE_CODE,
						imageCode);
				ImageIO.write(buferedImage, "jpeg", response.getOutputStream());
				return null;
			}
		};
		return callable;
	}

	@GetMapping("/code/captchabase64.jpg")
	public Callable<String> getImageCodeBase64(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Map<String, Object> result = new HashMap<String, Object>(2);
				ImageCodeProperties imageCodeProperties = securityProperties.getCode().getImage();
				String randomStr = RandomString.make(imageCodeProperties.getLength());
				String base64Str = "data:image/jepg;base64," + imageCodeGenerator.getRandomCodeBase64(new ServletWebRequest(request), randomStr);
				ImageCode imageCode = new ImageCode(randomStr, imageCodeProperties.getExpireIn());
				sessionStrategy.setAttribute(new ServletWebRequest(request), SecurityConstants.DEFAULT_SESSION_KEY_IMAGE_CODE, imageCode);
				result.put(AppConstants.DEFAULT_RETURN_MESSAGE, "验证码生成成功");
				result.put("base64", base64Str);
				Gson gson = new Gson();
				return gson.toJson(result);
			}
		};
		return callable;
	}

	@GetMapping("/code/sms")
	public Callable<String> createSmsCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletRequestBindingException {
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				SmsCode smsCodeInRedis = (SmsCode) smsCodeRedisOperation.get(new ServletWebRequest(request));
				Map<String, Object> result = new HashMap<String, Object>(2);
				SmsCodeProperties smsCodeProperties = securityProperties.getCode().getSms();
				int expireIn = smsCodeProperties.getExpireIn();
				if (smsCodeInRedis == null || smsCodeInRedis.isExpired()) {
					String mobile = ServletRequestUtils.getRequiredStringParameter(request,
							SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);
					int smsCodeLength = smsCodeProperties.getLength();
					int origin = Integer.valueOf("1".repeat(smsCodeLength));
					int bound = Integer.valueOf("9".repeat(smsCodeLength));
					String randomCode = String.valueOf(ThreadLocalRandom.current().nextInt(origin, bound));
					SmsCode smsCode = new SmsCode(randomCode, expireIn);
					boolean successBoolean = smsCodeSender.send(mobile, smsCode.getCode());
					if (successBoolean) {
						smsCodeRedisOperation.save(new ServletWebRequest(request), smsCode, smsCodeProperties.getExpireIn(), TimeUnit.SECONDS);
						result.put(AppConstants.DEFAULT_RETURN_MESSAGE, "短信验证码发送成功，请在" + (expireIn / 60) + "分钟内使用。");
					} else {
						result.put(AppConstants.DEFAULT_RETURN_MESSAGE, "短信验证码发送失败");
						// TODO 这里要做处理，联系管理员的操作，紧急的各种方式都可以
					}
				} else {
					result.put(AppConstants.DEFAULT_RETURN_MESSAGE, "短信验证码未过期");
					LocalDateTime expireTime = smsCodeInRedis.getExpireTime();
					LocalDateTime now = LocalDateTime.now();
					Duration duration = Duration.between(now, expireTime);
					result.put("expireIn", duration.toSeconds());
				}
				Gson gson = new Gson();
				return gson.toJson(result);
			}
		};
		return callable;
	}
}
