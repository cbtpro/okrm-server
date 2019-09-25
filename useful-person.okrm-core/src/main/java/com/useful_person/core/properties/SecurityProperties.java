package com.useful_person.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "okrm.security")
public class SecurityProperties {

	private BrowserProperties browser = new BrowserProperties();

	private ValidatorCodeProperties code = new ValidatorCodeProperties();

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
