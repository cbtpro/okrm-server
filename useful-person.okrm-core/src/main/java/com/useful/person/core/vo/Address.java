/**
 * 
 */
package com.useful.person.core.vo;

import com.useful.person.core.annotation.SensitiveInfo;
import com.useful.person.core.sensitive.SensitiveType;

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
	@SensitiveInfo(SensitiveType.LONGITUDE)
	private Double longitude;

	@Getter
	@Setter
	@SensitiveInfo(SensitiveType.LATITUDE)
	private Double latitude;

}
