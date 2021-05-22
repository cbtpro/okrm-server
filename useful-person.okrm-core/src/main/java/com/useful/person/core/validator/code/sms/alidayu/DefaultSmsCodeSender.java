package com.useful.person.core.validator.code.sms.alidayu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.useful.person.core.authentication.exception.GeneralException;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.properties.SmsCodeProperties;
import com.useful.person.core.validator.code.sms.SmsCodeSender;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author peter
 *
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Value("${spring.application.name}")
    private String projectName;

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
        request.setAction(SmsAction.SendSms.getValue());
        request.putQueryParameter("RegionId", smsCodeProperties.getRegionId());
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", smsCodeProperties.getSignName());
        request.putQueryParameter("TemplateCode", TemplateCode.SMS_7390148.getCode());
        request.putQueryParameter("TemplateParam",
                "{\"code\": \"" + code + "\", \"product\": \"" + projectName + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            Gson gson = new Gson();
            SmsResponse smsResponse = gson.fromJson(data, SmsResponse.class);
            String responseCode = smsResponse.getCode();
            String requestId = smsResponse.getRequestId();
            String bizId = smsResponse.getBizId();
            String message = smsResponse.getMessage();
            String responseMessage = "{ Code: \"" + responseCode + "\", RequestId: \"" + requestId + "\", BizId: \""
                    + bizId + "\", Message: \"" + message + "\" }";
            log.info("向手机：" + mobile + " 发送验证码：" + code + ", response：" + responseMessage);
            boolean isSuccess = smsResponse.getSendStatus();
            if (!isSuccess) {
                log.info("向手机：{} 发送验证码：{}, errMsg：{}", mobile, code, smsResponse.getMessage());
                throw new GeneralException(mobile, "短信验证码发送失败！");
            }
            return isSuccess;
        } catch (ServerException e) {
            e.printStackTrace();
            log.info("向手机：{} 发送验证码：{}, errMsg：{}", mobile, code, e.getErrMsg());
        } catch (ClientException e) {
            e.printStackTrace();
            log.info("向手机：" + mobile + " 发送验证码：" + code + ", errMsg：" + e.getErrMsg());
        }
        return false;
    }

}
