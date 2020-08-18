/**
 * 
 */
package com.useful.person.app;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

/**
 * @author peter
 *
 */
@Component
public class AppConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

}
