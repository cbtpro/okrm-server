/**
 * 
 */
package com.useful.person.core.sensitive;

import com.useful.person.core.utils.StringUtil;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * @author cbtpro
 *
 */
public class SensitiveInfoUtils {

	public static String chineseName(final String chineseName) {
		if(StringUtils.isBlank(chineseName)) {
			return "";
		}
		// TODO
		return chineseName;
	}

	public static String mobile(final String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return "";
		}
		return StringUtil.mobileDataMask(mobile);
	}

	public static String password(final String password) {
		return "******";
	}
	public static String email(final String email) {
		if (StringUtils.isBlank(email)) {
			return "";
		}
		return StringUtil.emailDataMask(email);
	}
	public static String identityCard(final String identityCard) {
		return identityCard;
	}
	public static String bankCard(final String bankCard) {
		return bankCard;
	}
}
