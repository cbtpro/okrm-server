package com.useful.person.core.validator.code;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.validator.code.captcha.ImageCode;

/**
 * 
 * @author peter
 *
 */
public class ValidatorCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String needCodeUrls = securityProperties.getCode().getImage().getUrl();
        String[] configUrls = needCodeUrls.split(",");
        for (String configUrl : configUrls) {
            urls.add(configUrl);
        }
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        boolean action = false;
        String requestUri = request.getRequestURI();
        for (String url : urls) {
            if (antPathMatcher.match(url, requestUri)) {
                action = true;
                break;
            }
        }
        if (action) {
            try {
                validate(request);
            } catch (ValidatorCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    private void validate(HttpServletRequest request) throws ServletRequestBindingException {
        String codeInRequest = (String) request.getParameter(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE);
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidatorCodeException("验证码不能为空");
        }
        ImageCode codeImageInSession = (ImageCode) request.getSession()
                .getAttribute(SecurityConstants.DEFAULT_SESSION_KEY_IMAGE_CODE);
        if (codeImageInSession == null) {
            throw new ValidatorCodeException("验证码不存在");
        }
        if (codeImageInSession.isExpired()) {
            throw new ValidatorCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(codeImageInSession.getCode(), codeInRequest)) {
            throw new ValidatorCodeException("验证码不匹配");
        }
        request.getSession().removeAttribute(SecurityConstants.DEFAULT_SESSION_KEY_IMAGE_CODE);
    }

}
