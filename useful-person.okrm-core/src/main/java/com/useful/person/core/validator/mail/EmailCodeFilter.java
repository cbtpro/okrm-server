/**
 * 
 */
package com.useful.person.core.validator.mail;

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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.redis.impl.EmailCodeRedisOperation;
import com.useful.person.core.validator.code.ValidatorCodeException;

/**
 * @author cbtpro
 *
 */
public class EmailCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private EmailCodeRedisOperation emailCodeRedisOperation;

    private AuthenticationFailureHandler authenticationFailureHandler;

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String needCodeUrls = securityProperties.getMail().getCode().getUrl();
        String[] configUrls = needCodeUrls.split(",");
        for (String configUrl : configUrls) {
            urls.add(configUrl);
        }
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
                validate(new ServletWebRequest(request));
            } catch (ValidatorCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    public void validate(ServletWebRequest request) throws ServletRequestBindingException {
        String emailCodeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_EMAIL);
        if (StringUtils.isBlank(emailCodeInRequest)) {
            throw new ValidatorCodeException("邮箱验证码不能为空！");
        }
        EmailCode emailCodeInRedis = (EmailCode) emailCodeRedisOperation.get(request);
        if (emailCodeInRedis == null) {
            throw new ValidatorCodeException("邮箱验证码不存在");
        }
        if (emailCodeInRedis.isExpired()) {
            throw new ValidatorCodeException("邮箱验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(emailCodeInRedis.getCode(), emailCodeInRequest)) {
            throw new ValidatorCodeException("邮箱验证码不匹配");
        }
        emailCodeRedisOperation.remove(request);
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

    public EmailCodeRedisOperation getEmailCodeRedisOperation() {
        return emailCodeRedisOperation;
    }

    public void setEmailCodeRedisOperation(EmailCodeRedisOperation emailCodeRedisOperation) {
        this.emailCodeRedisOperation = emailCodeRedisOperation;
    }

}
