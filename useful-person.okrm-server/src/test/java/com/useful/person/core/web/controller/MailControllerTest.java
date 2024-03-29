package com.useful.person.core.web.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author peter
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MailControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	private String email = "peter.chen@useful-person.com";

	@Before
	public void steup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void whenSendMailCodeSucess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/code/mail").param("email", email).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());
	};

	@Test
	public void whenSendMailCodeFail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/code/mail").param("email", email).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().is5xxServerError());
	};
}
