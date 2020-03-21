/**
 * 需要脱敏的类型
 */
package com.useful.person.core.sensitive;

/**
 * @author cbtpro
 *
 */
public enum SensitiveType {

	/**
	 * 中文名称
	 */
	CHINESE_NAME,

	/**
	 * 用户密码
	 */
	PASSWORD,

	/**
	 * 手机号
	 */
	MOBILE,

	/**
	 * email
	 */
	EMAIL,

	/**
	 * 身份证
	 */
	ID_CARD,

	/**
	 * 银行卡号
	 */
	BANK_CARD,

	/**
	 * 经度
	 */
	LONGITUDE,

	/**
	 * 纬度
	 */
	LATITUDE,
}
