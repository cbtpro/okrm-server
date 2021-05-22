package com.useful.person.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
@Order(0)
@ConfigurationProperties(prefix = "okrm.security")
public class SecurityProperties {

    @Getter
    @Setter
    private BrowserProperties browser = new BrowserProperties();

    @Getter
    @Setter
    private ValidatorCodeProperties code = new ValidatorCodeProperties();

    @Getter
    @Setter
    private MailProperties mail = new MailProperties();

    @Getter
    @Setter
    private OSSProperties oss = new OSSProperties();

}
