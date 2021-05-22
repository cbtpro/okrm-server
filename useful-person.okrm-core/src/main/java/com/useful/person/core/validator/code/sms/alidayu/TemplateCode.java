package com.useful.person.core.validator.code.sms.alidayu;

/**
 * 阿里云发送短信的模版枚举
 * 
 * @author peter
 *
 */
public enum TemplateCode {

    // 阿里云发送短信模版
    SMS_7390152("身份验证验证码", "SMS_7390152"), SMS_7390151("短信测试", "SMS_7390151"), SMS_7390150("登录确认验证码", "SMS_7390150"),
    SMS_7390149("登录异常验证码", "SMS_7390149"), SMS_7390148("用户注册验证码", "SMS_7390148"), SMS_7390147("活动确认验证码", "SMS_7390147"),
    SMS_7390146("修改密码验证码", "SMS_7390146"), SMS_7390145("信息变更验证码", "SMS_7390145");

    private String name;
    private String code;

    private TemplateCode(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
