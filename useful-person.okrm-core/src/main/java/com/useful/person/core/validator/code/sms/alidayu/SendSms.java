package com.useful.person.core.validator.code.sms.alidayu;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 
 * @author peter
 *
 */
public class SendSms {
	public static void main(String[] args) {
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FdGoNveVpKk6UFcLhmN", "ixv3q4iDT3A7AQaTjBLtCk76wVc20H");

		IAcsClient client = new DefaultAcsClient(profile);

		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setVersion("2017-05-25");
		request.setAction("SendSms");
		request.putQueryParameter("RegionId", "cn-hangzhou");
		request.putQueryParameter("PhoneNumbers", "18922886349");
		request.putQueryParameter("SignName", "生而不庸");
		request.putQueryParameter("TemplateCode", "SMS_7390148");
		request.putQueryParameter("TemplateParam", "{\"code\": \"1234\", \"product\": \"生而不庸\"}");
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}