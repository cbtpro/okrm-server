package com.useful.person.browser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.google.gson.Gson;
import com.useful.person.core.aliyun.api.gateway.constant.ContentType;
import com.useful.person.core.constants.ReturnCode;
import com.useful.person.core.vo.ResponseData;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(ContentType.CONTENT_TYPE_JSON);
		ResponseData<String> responseData = new ResponseData<>(ReturnCode.ERROR.getCode(), "用户未登录！", authException.getLocalizedMessage());
		Gson gson = new Gson();
		response.getWriter().write(gson.toJson(responseData));
	}

}
