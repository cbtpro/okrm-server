package com.useful_person.core.validator.code;

import java.awt.image.BufferedImage;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidatorCodeGenerator {

	BufferedImage buildImageCode(ServletWebRequest request, String randomStr);

}
