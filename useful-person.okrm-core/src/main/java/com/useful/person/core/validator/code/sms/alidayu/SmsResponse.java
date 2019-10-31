package com.useful.person.core.validator.code.sms.alidayu;

/**
 * https://error-center.aliyun.com/status/product/Dysmsapi?spm=a2c1g.8271268.10000.118.4773df25WBM8UY
 * 
 * @author peter
 *
 */
@SuppressWarnings("all")
public class SmsResponse {

	/**
	 * "Message":"OK",
	 * "RequestId":"2B4FFBCE-7CDD-4D5E-98B9-F45B2D122312",
	 * "BizId":"739014469727886950^0",
	 * "Code":"OK"
	 */
	/**
	 *  没有访问权限 or OK
	 */
	private String Message;

	/**
	 * 7653EE92-103A-4FB5-AEC2-F6E97D53AE79
	 */
	private String RequestId;

	/**
	 * 739014469727886950^0
	 */
	private String BizId;

	/**
	 * isp.RAM_PERMISSION_DENY" or OK
	 */
	private String Code;

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public String getBizId() {
		return BizId;
	}

	public void setBizId(String bizId) {
		BizId = bizId;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public boolean getSendStatus() {
		return this.Code.equalsIgnoreCase("OK") || this.Message.equalsIgnoreCase("OK");
	}
}
