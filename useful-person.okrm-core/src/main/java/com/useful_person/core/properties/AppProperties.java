package com.useful_person.core.properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "okrm.app")
public class AppProperties {

	@Getter
	@Setter
	private String host = "//localhost";

	@Getter
	@Setter
	private int port = 8080;

	public String getOrigin() {
		String origin = this.host;
		String port = StringUtils.trim(String.valueOf(this.port));
		if (StringUtils.isNotBlank(port) && !"80".equals(port)) {
			origin += (":" + this.port);
		}
		return  origin;
	}
}
