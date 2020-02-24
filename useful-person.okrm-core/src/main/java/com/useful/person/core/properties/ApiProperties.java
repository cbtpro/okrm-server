package com.useful.person.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
@Order(1)
@ConfigurationProperties(prefix = "okrm.api")
public class ApiProperties {

	@Getter
	@Setter
	private LianzhuoProperties lianzhuo = new LianzhuoProperties();

	@Getter
	@Setter
	private TencentProperties tencent = new TencentProperties();


}
