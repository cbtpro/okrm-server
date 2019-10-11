package com.useful_person.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.google.gson.Gson;
import com.useful_person.core.properties.AppProperties;
import com.useful_person.core.properties.OkrmConstants;
import com.useful_person.core.properties.SecurityConstants;
import com.useful_person.core.properties.SecurityProperties;
import com.useful_person.core.redis.impl.BasicRedisOperation;
import com.useful_person.core.validator.mail.MailService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(SecurityConstants.DEFAULT_ACTIVATE_URL_PREFIX)
public class UserActivationController {

	@Autowired
	private MailService mailService;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private BasicRedisOperation basicRedisOperation;

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private SecurityProperties securityProperties;

	@RequestMapping("/email")
	public Callable<String> sendActivateEmail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "to", required = true) String to) {
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Map<String, String> result = new HashMap<String, String>();
				Context context = new Context();
				context.setVariable("host", appProperties.getOrigin());
				context.setVariable("email", to);
				String activationUUID = UUID.randomUUID().toString();
				context.setVariable("uuid", activationUUID);
				String emailContent = templateEngine.process("signin-template", context);
				mailService.sendHtmlMail(to, "生而不庸激活邮件", emailContent);
				int expireIn = securityProperties.getMail().getVerification().getExpireIn();
				basicRedisOperation.save(to, activationUUID, expireIn, TimeUnit.SECONDS);
				result.put(OkrmConstants.DEFAULT_RETURN_MESSAGE, "邮件发送成功，请在" + (expireIn / 60 / 60 ) + "小时内使用。");
				Gson gson = new Gson();
				return gson.toJson(result);
			}
		};
		return callable;
	}

	@RequestMapping("/{email}/{uuid}")
	public Callable<String> verifyActiveMail(@PathVariable(name = "email", required = true) String email, @PathVariable(name = "uuid", required = true) String uuid) {
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Map<String, String> result = new HashMap<String, String>();
				String uuidInRedis = (String) basicRedisOperation.get(email);
				if (uuidInRedis == null) {
					result.put(OkrmConstants.DEFAULT_RETURN_MESSAGE, email + "的验证地址不存在，请在个人资料界面重新发送验证邮件！");
				} else if (!uuid.equals(uuidInRedis)) {
					result.put(OkrmConstants.DEFAULT_RETURN_MESSAGE, email + "的验证地址不正确，请在个人资料界面重新发送验证邮件！");
				} else {
					basicRedisOperation.remove(email);
					log.info("{} 验证通过！", email);
					result.put(OkrmConstants.DEFAULT_RETURN_MESSAGE, email + "地址验证通过，恭喜您！请畅快的畅游为您定制的世界吧！");
				}
				Gson gson = new Gson();
				return gson.toJson(result);
			}
		};
		return callable;
	}
}
