/**
 * 
 */
package com.useful.person.core.vo;

import com.useful.person.core.annotation.SensitiveInfo;
import com.useful.person.core.sensitive.SensitiveType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author cbtpro
 *
 */
@AllArgsConstructor
@ApiModel("VO用户位置")
public class Address {

	@Getter
	@Setter
	@ApiModelProperty(value = "用户唯一标识")
	private String uuid;

	@Getter
	@Setter
	@ApiModelProperty(value = "用户昵称")
	private String nickname;

	@Getter
	@Setter
	@ApiModelProperty(value = "用户头像")
	private String avatar;

	@Getter
	@Setter
	@SensitiveInfo(SensitiveType.LONGITUDE)
	@ApiModelProperty(value = "经度")
	private Double longitude;

	@Getter
	@Setter
	@SensitiveInfo(SensitiveType.LATITUDE)
	@ApiModelProperty(value = "纬度")
	private Double latitude;

}
