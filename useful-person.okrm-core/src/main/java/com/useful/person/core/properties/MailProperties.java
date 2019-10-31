package com.useful.person.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
public class MailProperties {
	
	@Getter
	@Setter
	private MailCodeProperties code;

	@Getter
	@Setter
	private MailVerificationProperties verification;

}
