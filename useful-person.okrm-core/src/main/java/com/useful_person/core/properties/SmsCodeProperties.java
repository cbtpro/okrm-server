package com.useful_person.core.properties;

public class SmsCodeProperties {

	private int length = 4;

	private int expireIn = 5 * 60;

	private String regionId = "cn-hangzhou";

	private String domain = "dysmsapi.aliyuncs.com";

	private String version = "2017-05-25";

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
