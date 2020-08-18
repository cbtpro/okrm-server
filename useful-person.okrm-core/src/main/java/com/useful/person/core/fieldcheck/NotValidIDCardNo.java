package com.useful.person.core.fieldcheck;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Constraint(validatedBy = IDCardNoValidRule.class)
@Retention(RUNTIME)
public @interface NotValidIDCardNo {

	/**
	 * 参数名
	 * @return
	 */
	String message() default "身份证格式不正确";
	/**
	 * 分组
	 * @return
	 */
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
