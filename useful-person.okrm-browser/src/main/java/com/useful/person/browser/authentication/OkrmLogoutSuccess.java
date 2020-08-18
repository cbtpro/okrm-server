///**
// * 
// */
//package com.useful.person.browser.authentication;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.useful.person.core.properties.AppConstants;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author peter
// *
// */
//@Slf4j
//@Component("okrmLogoutSuccess")
//public class OkrmLogoutSuccess extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
//
//	@Autowired
//	private ObjectMapper objectMapper;
//	
//	@Autowired
////	TokenBasedRememberMeServices tokenBasedRememberMeServices;
////	private PersistentTokenRepository persistentTokenRepository;
//
//	@Override
//	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
//			throws IOException, ServletException {
////		persistentTokenRepository.removeUserTokens(authentication.getName());
//		log.info("退出成功！");
//		response.setStatus(HttpStatus.OK.value());
//		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//		Map<String, String> result = new HashMap<String, String>();
//		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, "登出成功！");
//		response.getWriter().write(objectMapper.writeValueAsString(result));
////		super.onLogoutSuccess(request, response, authentication);
//	}
//
//}
