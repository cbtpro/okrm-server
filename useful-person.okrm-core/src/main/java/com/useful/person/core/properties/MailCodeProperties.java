package com.useful.person.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
public class MailCodeProperties {

	@Getter
	@Setter
	private int expireIn = 60 * 30;

	@Getter
	@Setter
	private String url = "";

}
