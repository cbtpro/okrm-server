package com.useful.person.core.validator.mail;

/**
 * 
 * @author peter
 *
 */
public interface MailService {

    /**
     * 发送简单邮件
     * 
     * @param to
     * @param subject
     * @param content
     */
    @Deprecated
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送html邮件
     * 
     * @param to
     * @param subject
     * @param content
     */
    void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件Ô
     * 
     * @param to
     * @param subject
     * @param content
     * @param attachmentPath
     */
    void sendAttachmentMail(String to, String subject, String content, String attachmentPath);

}
