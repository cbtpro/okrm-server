package com.useful.person.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.useful.person.core.properties.SecurityProperties;

/**
 * 
 * @author peter
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
