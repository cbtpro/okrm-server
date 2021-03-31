package com.useful.person.core.web.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.useful.person.core.constants.ReturnCode;
import com.useful.person.core.properties.MailCodeProperties;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.redis.impl.EmailCodeRedisOperation;
import com.useful.person.core.validator.mail.EmailCode;
import com.useful.person.core.validator.mail.MailService;
import com.useful.person.core.vo.ResponseData;

import io.swagger.annotations.Api;

/**
 * 
 * @author peter
 *
 */
@RestController
@RequestMapping("/code")
@Api(value = "邮箱controller", tags = { "邮箱操作接口" } )
public class MailController {

	@Autowired
	private MailService mailService;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private EmailCodeRedisOperation emailCodeRedisOperation;

	@Autowired
	private SecurityProperties securityProperties;
//
//	@GetMapping("/testmail")
//	public Callable<String> testMail(HttpServletRequest request, HttpServletResponse response,
//			@RequestParam(name = "email", required = true) String email) {
//		Callable<String> callable = new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				mailService.sendHtmlMail(email, "测试邮件", "这是一封测试邮件，如有打扰，请见谅。感谢您对生而不庸的支持，希望每一天的您都比昨天的您优秀！这是一封自动产生的邮件，请勿尝试回复。");
//				Gson gson = new Gson();
//				Map<String, String> result = new HashMap<String, String>();
//				result.put(OkrmConstants.DEFAULT_RETURN_MESSAGE, "邮件发送到 " + email + " 成功");
//				return gson.toJson(result);
//			}
//		};
//		return callable;
//	}

	@GetMapping("/mail")
	public Callable<ResponseData<String>> sendMailCode(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "email", required = true) String email) {
		Callable<ResponseData<String>> callable = new Callable<ResponseData<String>>() {
			@Override
			public ResponseData<String> call() throws Exception {
				ResponseData<String> responseData = null;
				MailCodeProperties mailCodeProperties = securityProperties.getMail().getCode();
				int expireIn = mailCodeProperties.getExpireIn();
				// 判断验证码是否过期
				EmailCode emailCodeInRedis = emailCodeRedisOperation.get(new ServletWebRequest(request));
				if (emailCodeInRedis == null || emailCodeInRedis.isExpired()) {
					String randomCode = String.valueOf(ThreadLocalRandom.current().nextInt(1111, 9999));
					EmailCode emailCode = new EmailCode(randomCode, expireIn);
					Context verificationCodeContext = new Context();
					verificationCodeContext.setVariable("verificationCode", randomCode);
					String verificationCodeMailContent = templateEngine.process("verification-code-template",
							verificationCodeContext);
					mailService.sendHtmlMail(email, "验证码" + randomCode, verificationCodeMailContent);
					emailCodeRedisOperation.save(new ServletWebRequest(request), emailCode, expireIn, TimeUnit.SECONDS);
					responseData = new ResponseData<String>(ReturnCode.CORRECT.getCode(), "邮件验证码发送成功，请在" + (expireIn / 60) + "分钟内使用。", null);
				} else {
					LocalDateTime expireTime = emailCodeInRedis.getExpireTime();
					LocalDateTime now = LocalDateTime.now();
					Duration duration = Duration.between(now, expireTime);
					responseData = new ResponseData<String>(ReturnCode.ERROR.getCode(), "邮箱验证码还有" + duration.toSeconds() + "秒过期！", null);
				}
				return responseData;
			}
		};
		return callable;
	}

}
