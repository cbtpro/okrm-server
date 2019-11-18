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

	private BrowserProperties browser = new BrowserProperties();

	private ValidatorCodeProperties code = new ValidatorCodeProperties();

	@Getter
	@Setter
	private MailProperties mail = new MailProperties();

	public ValidatorCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidatorCodeProperties code) {
		this.code = code;
	}

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

}
