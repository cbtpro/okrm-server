package com.useful_person.core.properties;

public class BrowserProperties {

	private String signinPage = "/basic-signin.html";

	private SigninType signinType = SigninType.JSON;

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

}
