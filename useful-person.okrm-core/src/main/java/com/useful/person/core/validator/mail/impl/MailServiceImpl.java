package com.useful.person.core.validator.mail.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.useful.person.core.validator.mail.MailService;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author peter
 *
 */
@Service("mailService")
@Log4j2
public class MailServiceImpl implements MailService {

	@Value("${spring.application.name}")
	private String projectName;

	@Autowired
	private JavaMailSenderImpl javaMailSender;

	@Value("${spring.mail.username}")
	private String from;

	@Override
	@Deprecated
	public void sendSimpleMail(String to, String subject, String content) {
		log.debug("Send e-mail to {} with subject {} and content={}", to, subject, content);
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(to);
		simpleMessage.setSubject(subject);
		simpleMessage.setText(content);
		simpleMessage.setFrom(from);
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.ssl.enable", true);
		javaMailSender.setJavaMailProperties(javaMailProperties);
		javaMailSender.send(simpleMessage);
		log.debug("Sent e-mail to {}", to);
	}

	@Override
	public void sendHtmlMail(String to, String subject, String content) {
		log.info("发送HTML邮件开始：{}， {}， {}。", to, subject, content);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(MimeUtility.encodeText("不平凡的人") + " <" + to + ">"));
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(content, true);
			mimeMessage.setFrom(new InternetAddress(MimeUtility.encodeText(projectName) + " <" + from + ">"));
			Properties javaMailProperties = new Properties();
			javaMailProperties.put("mail.smtp.auth", "true");
			javaMailProperties.put("mail.smtp.ssl.enable", true);
			javaMailSender.setJavaMailProperties(javaMailProperties);
			javaMailSender.send(mimeMessage);
			log.info("发送HTML邮件成功。");
		} catch (MessagingException e) {
			log.info("发送HTML邮件失败，消息：" + e);
		} catch (UnsupportedEncodingException e) {
			log.info("发送HTML邮件失败，消息：" + e);
		}
	}

	@Override
	public void sendAttachmentMail(String to, String subject, String content, String attachmentPath) {
		log.info("发送带附件邮件开始：{}, {}, {}, {}", to, subject, content, attachmentPath);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(from);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(content, true);
			FileSystemResource file = new FileSystemResource(new File(attachmentPath));
			String fileName = file.getFilename();
			// 添加附件，可多次调用该方法添加多个附件
			mimeMessageHelper.addAttachment(fileName, file);
			javaMailSender.send(mimeMessage);
			log.info("发送带附件邮件成功。");
		} catch (MessagingException e) {
			log.error("发送带附件邮件失败，消息：", e);
		}
	}

}
