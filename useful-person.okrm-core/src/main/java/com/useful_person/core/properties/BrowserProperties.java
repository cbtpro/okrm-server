package com.useful_person.core.properties;

public class BrowserProperties {

	private String signinPage = "/basic-signin.html";

	private SigninType signinType = SigninType.JSON;

	private int rememberMeSeconds = 3600;

	public String getSigninPage() {
		return signinPage;
	}

	public void setSigninPage(String signinPage) {
		this.signinPage = signinPage;
	}

	public SigninType getSigninType() {
		return signinType;
	}

	public void setSigninType(SigninType signinType) {
		this.signinType = signinType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

}
