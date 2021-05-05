/**
 * 
 */
package com.useful.person.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

import com.useful.person.core.properties.SecurityConstants;

/**
 * 
 * @author peter
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.CLASS)
@PreAuthorize("hasRole('" + SecurityConstants.DEFAULT_ROLE_NAME_PREFIX + "TEST')")
public @interface HasTestRole {

	String value() default "";
}
