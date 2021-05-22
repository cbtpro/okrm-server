package com.useful.person.core.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 
 * @author peter
 *
 */
@Aspect
@Component
public class LogRecordAspect {

    @Around("execution(* com.useful.person.core.web.controller.UserController.*(..)) && @annotation(org.springframework.web.bind.annotation.PostMapping)\n"
            + "	@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        Object object = pjp.proceed();
        return object;
    }
}
