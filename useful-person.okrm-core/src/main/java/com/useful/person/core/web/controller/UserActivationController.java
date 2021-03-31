package com.useful.person.core.web.controller;

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
import com.useful.person.core.properties.AppConstants;
import com.useful.person.core.properties.AppProperties;
import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.redis.impl.BasicRedisOperation;
import com.useful.person.core.validator.mail.MailService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author peter
 *
 */
@Slf4j
@RestController
@RequestMapping(SecurityConstants.DEFAULT_ACTIVATE_URL_PREFIX)
@Api(value = "用户激活controller", tags = { "用户激活操作接口" } )
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
			@RequestParam(name = "email", required = true) String email) {
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Map<String, String> result = new HashMap<String, String>(2);
				Context context = new Context();
				context.setVariable("host", appProperties.getOrigin());
				context.setVariable("email", email);
				String activationUuid = UUID.randomUUID().toString();
				context.setVariable("uuid", activationUuid);
				String emailContent = templateEngine.process("signin-template", context);
				mailService.sendHtmlMail(email, "生而不庸激活邮件", emailContent);
				int expireIn = securityProperties.getMail().getVerification().getExpireIn();
				basicRedisOperation.save(email, activationUuid, expireIn, TimeUnit.SECONDS);
				result.put(AppConstants.DEFAULT_RETURN_MESSAGE, "邮件发送成功，请在" + (expireIn / 60 / 60 ) + "小时内使用。");
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
				Map<String, String> result = new HashMap<String, String>(2);
				String uuidInRedis = (String) basicRedisOperation.get(email);
				if (uuidInRedis == null) {
					result.put(AppConstants.DEFAULT_RETURN_MESSAGE, email + "的验证链接地址不存在，请在个人资料界面重新发送验证邮件！");
				} else if (!uuid.equals(uuidInRedis)) {
					result.put(AppConstants.DEFAULT_RETURN_MESSAGE, email + "的验证链接地址不正确，请在个人资料界面重新发送验证邮件！");
				} else {
					basicRedisOperation.remove(email);
					log.info("{} 验证通过！", email);
					result.put(AppConstants.DEFAULT_RETURN_MESSAGE, email + "地址验证通过，恭喜您！开始畅游塑造不平凡的人生吧！");
				}
				Gson gson = new Gson();
				return gson.toJson(result);
			}
		};
		return callable;
	}
}
