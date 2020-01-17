package com.useful.person.core.exception;

import lombok.Getter;
import lombok.Setter;

public class OSSException extends RuntimeException {

	private static final long serialVersionUID = 3449140032830774548L;

	@Getter
	@Setter
	public String msg;

	public OSSException(String msg) {
		super("上传文件至OSS异常");
		this.msg = msg;
	}
}
