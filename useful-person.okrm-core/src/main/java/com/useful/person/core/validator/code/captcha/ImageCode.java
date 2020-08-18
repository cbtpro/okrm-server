package com.useful.person.core.validator.code.captcha;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @author peter
 *
 */
public class ImageCode implements Serializable {

	private static final long serialVersionUID = -4826893818867302046L;

	private String code;

	private LocalDateTime expireTime;

	public ImageCode(String code, int expireIn) {
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}

	public ImageCode(String code, LocalDateTime expireTime) {
		this.code = code;
		this.expireTime = expireTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expireTime);
	}
}
