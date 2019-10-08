package com.useful_person.core.validator.code.sms.alidayu;

/**
 * https://api.aliyun.com/new#/?product=Dysmsapi&api=SendSms&tab=DEMO&lang=JAVA
 * 
 * @author peter
 *
 */
public enum SmsAction {

	SendSms("发送短信", "SendSms"), QuerySendDetails("查看短信发送记录和发送状态", "QuerySendDetails"),
	QuerySmsSign("查询短信签名申请状态", "QuerySmsSign"), QuerySmsTemplate("查询短信模板的审核状态", "QuerySmsTemplate"),
	AddSmsSign("申请短信签名", "AddSmsSign"), AddSmsTemplate("申请短信模板", "AddSmsTemplate"),
	DeleteSmsSign("删除短信签名", "DeleteSmsSign"), DeleteSmsTemplate("删除短信模板", "DeleteSmsTemplate"),
	ModifySmsSign("修改未审核通过的短信签名", "ModifySmsSign"), ModifySmsTemplate("修改未通过审核的短信模板", "ModifySmsTemplate"),
	SendBatchSms("批量发送短信", "SendBatchSms");

	private String name;
	private String value;

	SmsAction(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
