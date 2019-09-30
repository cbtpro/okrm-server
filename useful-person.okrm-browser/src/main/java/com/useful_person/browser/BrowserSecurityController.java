package com.useful_person.browser;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.useful_person.browser.support.SimpleResponse;
import com.useful_person.core.properties.SecurityProperties;

@RestController
public class BrowserSecurityController {

//	private Logger logger = LoggerFactory.getLogger(getClass());
	public static final String AUTHENTICATION_REQUIRE_URL = "/authentication/require";

	private RequestCache requestCache = new HttpSessionRequestCache();

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private SecurityProperties securityProperties;

	@RequestMapping(AUTHENTICATION_REQUIRE_URL)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		SavedRequest saveRequest = requestCache.getRequest(request, response);

		if (saveRequest != null) {
			String redirectUrl = saveRequest.getRedirectUrl();
			if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
				redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getSigninPage());
			}
		}
		return new SimpleResponse("访问的服务需要登录");
	}
}
