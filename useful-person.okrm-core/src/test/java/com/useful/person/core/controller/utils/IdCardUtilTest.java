/**
 * 
 */
package com.useful.person.core.controller.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.useful.person.core.utils.IdCardUtil;

/**
 * @author cbtpro
 *
 */
public class IdCardUtilTest {

	private String idCard = "370703198111300338";

	private String name = "郭德昌";

	@Test
	public void testCheckIdCardNo() {
		assertTrue(IdCardUtil.checkIdCardNo(idCard));
	}
}
