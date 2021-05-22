package com.useful.person.core.i18n;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

@Component
public class MessageSourceHandler {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, RequestContextUtils.getLocale(request));
    }
}
