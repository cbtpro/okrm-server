/**
 * 标注敏感信息的注解
 */
package com.useful.person.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.useful.person.core.sensitive.SensitiveInfoSerialize;
import com.useful.person.core.sensitive.SensitiveType;

/**
 * @author cbtpro
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveInfoSerialize.class)
public @interface SensitiveInfo {

	public SensitiveType value();
}
