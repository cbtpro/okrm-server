/**
 * 
 */
package com.useful.person.core.sensitive;

import java.util.Random;

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

	/**
	 * 对经度脱敏
	 * @param longitude
	 * @return longitude 脱敏后的经度
	 */
	public static Double longitude(final Double longitude) {
		Double random = Math.random() / 4000;
		if (new Random().nextInt(2) == 0) {
			random = 0 - random;
		}
		return longitude + random;
	}

	/**
	 * 对纬度脱敏
	 * @param latitude
	 * @return latitude 脱敏后的纬度
	 */
	public static Double latitude(final Double latitude) {
		Double random = Math.random() / 2000;
		if (new Random().nextInt(2) == 0) {
			random = 0 - random;
		}
		return latitude + random;
	}
}
