package com.useful_person.core.properties;

import lombok.Getter;
import lombok.Setter;

public class MailVerificationProperties {

	@Getter
	@Setter
	private int expireIn = 60 * 60 * 24 * 2;

}
