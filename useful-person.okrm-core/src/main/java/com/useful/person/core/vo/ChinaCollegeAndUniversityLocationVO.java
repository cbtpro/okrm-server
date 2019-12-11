/**
 * 
 */
package com.useful.person.core.vo;

import lombok.Getter;
import lombok.Setter;

public class ChinaCollegeAndUniversityLocationVO {

	@Getter
	@Setter
	private String uuid;

	@Getter
	@Setter
	private long number;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private float longitude;

	@Getter
	@Setter
	private float latitude;

	public ChinaCollegeAndUniversityLocationVO(String uuid, long number, String name, float longitude, float latitude) {
		this.uuid = uuid;
		this.number = number;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}
}