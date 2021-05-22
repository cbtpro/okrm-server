/**
 * 
 */
package com.useful.person.core.validator.mail;

import java.time.LocalDateTime;

import com.useful.person.core.validator.code.sms.SmsCode;

/**
 * @author cbtpro
 *
 */
public class EmailCode extends SmsCode {

    private static final long serialVersionUID = -8726198540078820189L;

    public EmailCode(String code, int expireIn) {
        super(code, expireIn);
    }

    public EmailCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }

}
