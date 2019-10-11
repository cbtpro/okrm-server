package com.useful_person.core.properties;

import lombok.Getter;
import lombok.Setter;

public class MailCodeProperties {

	@Getter
	@Setter
	private int expireIn = 60 * 30;

}
