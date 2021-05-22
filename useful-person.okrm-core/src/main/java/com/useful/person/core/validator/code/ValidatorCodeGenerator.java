package com.useful.person.core.validator.code;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 
 * @author peter
 *
 */
public interface ValidatorCodeGenerator {

    /**
     * 生成图片验证码
     * 
     * @param request
     * @param randomStr
     * @return BufferedImage
     */
    BufferedImage buildImageCode(ServletWebRequest request, String randomStr);

    /**
     * 生成base64格式的图形验证码
     * 
     * @param request
     * @param randomStr
     * @return
     */
    String getRandomCodeBase64(ServletWebRequest request, String randomStr) throws IOException;
}
