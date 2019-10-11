package com.useful_person.core.validator.mail;

public interface MailService {

	@Deprecated
	void sendSimpleMail(String to, String subject, String content);

	void sendHtmlMail(String to, String subject, String content);

	void sendAttachmentMail(String to, String subject, String content, String attachmentPath);

}
