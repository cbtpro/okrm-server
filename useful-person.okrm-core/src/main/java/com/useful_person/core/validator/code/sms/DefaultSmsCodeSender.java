package com.useful_person.core.validator.code.sms;

import org.springframework.beans.factory.annotation.Autowired;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.useful_person.core.properties.SecurityProperties;
import com.useful_person.core.properties.SmsCodeProperties;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DefaultSmsCodeSender implements SmsCodeSender {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public boolean send(String mobile, String code) {
		SmsCodeProperties smsCodeProperties = securityProperties.getCode().getSms();
		DefaultProfile profile = DefaultProfile.getProfile(smsCodeProperties.getRegionId(),
				smsCodeProperties.getAccessKeyId(), smsCodeProperties.getSecret());
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain(smsCodeProperties.getDomain());
		request.setVersion(smsCodeProperties.getVersion());
		request.setAction("SendSms");
		request.putQueryParameter("RegionId", "cn-hangzhou");
		request.putQueryParameter("PhoneNumbers", mobile);
		request.putQueryParameter("SignName", "生而不庸");
		request.putQueryParameter("TemplateCode", "SMS_7390148");
		request.putQueryParameter("TemplateParam", "{\"code\": \"" +  code + "\", \"product\": \"生而不庸\"}");
		try {
			CommonResponse response = client.getCommonResponse(request);
			String data = response.getData();
			log.info("向手机：" + mobile + " 发送验证码：" + code + ", response：" + data);
			Gson gson = new Gson();
			SmsResponse smsResponse = gson.fromJson(data, SmsResponse.class);
			return smsResponse.getSendStatus();
		} catch (ServerException e) {
			e.printStackTrace();
			log.info("向手机：" + mobile + " 发送验证码：" + code + ", errMsg：" + e.getErrMsg());
		} catch (ClientException e) {
			e.printStackTrace();
			log.info("向手机：" + mobile + " 发送验证码：" + code + ", errMsg：" + e.getErrMsg());
		}
		return false;
	}

}
