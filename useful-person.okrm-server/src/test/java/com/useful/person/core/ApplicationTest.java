//package com.useful.person.core;
//
//import org.jasypt.encryption.StringEncryptor;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.useful.person.OkrmServerApplication;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = OkrmServerApplication.class)
//public class ApplicationTest {
//
//	@Autowired
//	private StringEncryptor stringEncryptor;
//
//	@Test
//	public void testEnvironmentProperties() {
//		System.out.println(stringEncryptor.encrypt("jiaxing"));
//		
//		System.out.println(stringEncryptor.decrypt("uO36s6nRZvKkQtNuwhZwR0O8aigqsrWnKap3PTbHpqjuqlM3Qoz7HRKCqIWDDgVucxPgjxgmFSg6wNfF/K20BQ=="));
//	}
//}