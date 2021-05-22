package com.useful.person.core.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.useful.person.core.constants.ErrorType;
import com.useful.person.core.exception.BadRequestException;
import com.useful.person.core.exception.InternalServerException;
import com.useful.person.core.exception.InvokeOtherServerException;
import com.useful.person.core.i18n.MessageSourceHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSourceHandler messageSourceHandler;

    private String handleException(Exception e, String code) {
        return handleException(e, code, null);
    }

    private String handleException(Exception e, String code, Object body) {
        String messageKey = e.getMessage();
        String message = messageKey;
        try {
            message = messageSourceHandler.getMessage(messageKey);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        if (StringUtils.isEmpty(message)) {
            if (StringUtils.isEmpty(messageKey)) {
                message = messageSourceHandler.getMessage(ErrorType.INTERNAL_SERVER_ERROR.getMessage());
            } else {
                message = messageKey;
            }
        }
        return message;
    }

    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequestException(BadRequestException e) {
        return handleException(e, e.getCode());
    }

    @ExceptionHandler(InternalServerException.class)
    public String handleInteralServerException(InternalServerException e) {
        return handleException(e, e.getCode());
    }

    @ExceptionHandler(InvokeOtherServerException.class)
    public String handleInvokeOtherServerException(InvokeOtherServerException e) {
        return handleException(e, e.getCode(), e.getBody());
    }
}
