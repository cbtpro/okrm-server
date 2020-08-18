/**
 * 
 */
package com.useful.person.core.validator.mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.useful.person.core.properties.MailConfig;
import com.useful.person.core.properties.SecurityProperties;

/**
 * @author peter
 *
 */
@Configuration
public class MailSender {

	@Autowired
	private SecurityProperties securityProperties;

	@Bean("javaMailSender")
	public JavaMailSenderImpl javaMailSenderConfiguration() {
		MailConfig mailConfig = securityProperties.getMail().getConfig();
		String host = mailConfig.getHost();
		int port = Integer.valueOf(mailConfig.getPort());
		String username = mailConfig.getUsername();
		String password = mailConfig.getPassword();
		String defaultEncoding = mailConfig.getDefaultEncoding();
		long timeout = mailConfig.getTimeout();
		long connectiontimeout = mailConfig.getConnectiontimeout();
		long writetimeout = mailConfig.getWritetimeout();
		boolean debug = Boolean.valueOf(mailConfig.getDebug());
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);
		javaMailSender.setDefaultEncoding(defaultEncoding);
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.ssl.enable", true);
		javaMailProperties.put("mail.smtp.timeout", timeout);
		javaMailProperties.put("mail.smtp.connectiontimeout", connectiontimeout);
		javaMailProperties.put("mail.smtp.writetimeout", writetimeout);
		javaMailProperties.put("mail.debug", debug);
		javaMailSender.setJavaMailProperties(javaMailProperties);
		return javaMailSender;
	}
}
