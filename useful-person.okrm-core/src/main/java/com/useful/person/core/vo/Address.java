/**
 * 
 */
package com.useful.person.core.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author cbtpro
 *
 */
@AllArgsConstructor
public class Address {

	@Getter
	@Setter
	private String uuid;

	@Getter
	@Setter
	private String nickname;

	@Getter
	@Setter
	private String avatar;

	@Getter
	@Setter
	private Double longitude;

	@Getter
	@Setter
	private Double latitude;

}
