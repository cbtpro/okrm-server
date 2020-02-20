/**
 * 
 */
package com.useful.person.core.controller.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.useful.person.core.utils.StringUtil;

/**
 * @author cbtpro
 *
 */
public class StringUtilTest {

	String mobile = "+86-18922886300";

	String chineseName = "陈躺躺";

	String email = "peter.chen@useful-person.com";
	String email1 = "cbtpro@163.com";
	String email2 = "cbtpro@gmail.com";
	String email3 = "361751277@qq.com";
	String email4  = "peter@chenbitao.com";

	String identityCard = "43068219900920405X";

	String bankCard = "6226097551656748";

	@Test public void testChineseNameDataMask() {
		String mask = StringUtil.chineseNameDataMask(chineseName);
		System.out.println("脱敏后的姓名：" + mask);
		assertEquals(mask, "**躺");
	}
	@Test public void testCheckMobile() {
		assertTrue(StringUtil.checkMobile(mobile));
	}

	@Test public void testMobileDataMask() {
		String mask = StringUtil.mobileDataMask(mobile);
		System.out.println("脱敏后的手机号：" + mask);
		assertEquals(mask, "+86-189******00");
	}

	@Test public void testEmailDataMask() {
		String mask = StringUtil.emailDataMask(email);
		System.out.println("脱敏后的email：" + mask);
		assertEquals(mask, "pete******@useful-person.com");
	}

	@Test public void testEmail1DataMask() {
		String mask = StringUtil.emailDataMask(email1);
		System.out.println("脱敏后的email1：" + mask);
		assertEquals(mask, "cbt***@163.com");

	}

	@Test public void testEmail2DataMask() {
		String mask = StringUtil.emailDataMask(email2);
		System.out.println("脱敏后的email2：" + mask);
		assertEquals(mask, "cbt***@gmail.com");
	}

	@Test public void testEmail3DataMask() {
		String mask = StringUtil.emailDataMask(email3);
		System.out.println("脱敏后的email3：" + mask);
		assertEquals(mask, "3617*****@qq.com");
	}

	@Test public void testEmail4DataMask() {
		String mask = StringUtil.emailDataMask(email4);
		System.out.println("脱敏后的email4：" + mask);
		assertEquals(mask, "pe***@chenbitao.com");
	}
	@Test public void testIdentityCardMask() {
		String mask = StringUtil.idCardDataMask(identityCard);
		System.out.println("脱敏后的身份证号：" + mask);
		assertEquals(mask, "430****405X");
	}
	@Test public void testBankCardMask() {
		String mask = StringUtil.bankCardDataMask(bankCard);
		System.out.println("脱敏后的银行卡号：" + mask);
		assertEquals(mask, "****6748");
	}
}
