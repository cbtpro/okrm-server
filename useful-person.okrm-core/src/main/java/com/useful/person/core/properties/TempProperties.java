/**
 * 
 */
package com.useful.person.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
public class TempProperties {

	@Getter
	@Setter
	private String dir;

	@Getter
	@Setter
	private int expireIn = 86400;
}
