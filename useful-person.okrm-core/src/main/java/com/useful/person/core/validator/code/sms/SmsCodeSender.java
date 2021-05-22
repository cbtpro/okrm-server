package com.useful.person.core.validator.code.sms;

/**
 * 
 * @author peter
 *
 */
public interface SmsCodeSender {

    /**
     * 发送验证码到手机
     * 
     * @param mobile
     * @param code
     * @return
     */
    boolean send(String mobile, String code);
}
