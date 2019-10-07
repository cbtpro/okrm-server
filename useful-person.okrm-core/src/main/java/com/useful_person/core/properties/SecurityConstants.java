package com.useful_person.core.properties;

public interface SecurityConstants {

	String DEFAULT_VALIDATOR_CODE_URL_PREFIX = "/code";

	String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

	String DEFAULT_UNAUTHENTICATION_FAILURE_URL = "/authentication/failure";

	String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";

	String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/authentication/mobile";

	String DEFAULT_SIGN_IN_PAGE_URL = "/basic-signin.html";

	String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

	String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

	String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

}
