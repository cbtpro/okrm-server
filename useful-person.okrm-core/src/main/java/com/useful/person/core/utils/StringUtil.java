/**
 * 字符串工具类
 */
package com.useful.person.core.utils;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * @author cbtpro
 *
 */
public class StringUtil {

	/**
	 * email正则表达式
	 */
	public static final String EMAIL_REGEX = "[a-zA-Z0-9_.]{1,}@[a-zA-Z0-9-_.]{1,}";

	/**
	 * 手机号正则，原则上对手机号的格式不做判断，手机号由短信验证码来判断
	 */
	public static final String MOBILE_REGEX = "^([+]?\\d{1,3}[-]?)(\\d{0,3})(\\d{6})(\\d{2})$";

	/**
	 * 手机号脱敏替换正则
	 */
	public static final String MOBILE_MASK_REPLACE_REGEX = "$1$2******$4";

	/**
	 * 检查手机号格式
	 * @param mobile
	 * @return boolean 字符串是否是手机号格式
	 */
	public static final boolean checkMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return false;
		}
		return mobile.matches(MOBILE_REGEX);
	}

	/**
	 * 手机号脱敏处理
	 * @param mobile
	 * @return String 脱敏后的手机号
	 */
	public static final String mobileDataMask(String mobile) {
		if (checkMobile(mobile)) {
			return mobile.replaceAll(MOBILE_REGEX, MOBILE_MASK_REPLACE_REGEX);
		}
		return mobile;
	}

	/**
	 * 中文名字脱敏处理
	 * @param chineseName
	 * @return String 脱敏后的中文名字
	 */
	public static final String chineseNameDataMask(String chineseName) {
		if (StringUtils.isBlank(chineseName)) {
			return chineseName;
		}
		int l = chineseName.length();
		return "*".repeat(l - 1) + chineseName.substring(l - 1);
	}

	public static final boolean checkEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return false;
		}
		return email.matches(EMAIL_REGEX);
	}
	/**
	 * email地址脱敏处理
	 * @param email
	 * @return String 脱敏后的email地址
	 */
	public static final String emailDataMask(String email) {
		if (checkEmail(email)) {
			int l = email.length();
			String address = email.substring(0, email.indexOf("@"));
			int addressLength = address.length();
			int half = addressLength / 3;
			return email.substring(0, half + 1) + "*".repeat(addressLength - half - 1) + email.substring(addressLength, l);
		}
		return email;
	}

	public static final String idCardNameMask(String idcardName) {
		return IdCardUtil.IdCardNameMask(idcardName);
	}
	/**
	 * 身份证脱敏处理
	 * @param idcard
	 * @return String 脱敏后的身份证信息
	 */
	public static final String idCardDataMask(String idcard) {
		if (IdCardUtil.checkIdCardNo(idcard)) {
			return IdCardUtil.IdCardNoMask(idcard);
		}
		return idcard;
	}

	public static final String bankCardDataMask(String bankCard) {
		if (BankCardUtil.checkBankCard(bankCard)) {
			return "****" + bankCard.substring(bankCard.length() - 4);
		}
		return bankCard;
	}
}
