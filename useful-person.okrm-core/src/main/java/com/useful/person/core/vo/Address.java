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

//	public interface AddressView {}

	@Getter
	@Setter
//	@JsonView(AddressView.class)
	private String uuid;

	@Getter
	@Setter
//	@JsonView(AddressView.class)
	private String nickname;

	@Getter
	@Setter
//	@JsonView(AddressView.class)
	private String avatar;

	@Getter
	@Setter
//	@JsonView(AddressView.class)
	@SensitiveInfo(SensitiveType.LONGITUDE)
	private Double longitude;

	@Getter
	@Setter
//	@JsonView(AddressView.class)
	@SensitiveInfo(SensitiveType.LATITUDE)
	private Double latitude;

}
