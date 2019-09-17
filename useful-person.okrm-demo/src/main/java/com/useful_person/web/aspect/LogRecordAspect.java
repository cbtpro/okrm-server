package com.useful_person.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogRecordAspect {

	@Around("execution(* com.useful_person.web.controller.UserController.*(..)) && @annotation(org.springframework.web.bind.annotation.PostMapping)\n"
			+ "	@annotation(org.springframework.web.bind.annotation.PutMapping)")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("进入切面");
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("arg is " + arg);
		}
		Object object = pjp.proceed();
		return object;
	}
}
