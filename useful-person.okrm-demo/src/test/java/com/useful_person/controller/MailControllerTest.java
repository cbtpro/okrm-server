package com.useful_person.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MailControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	private String to = "peter.chen@useful-person.com";

	@Before
	public void steup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void whenSendMailCodeSucess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/code/mail").param("to", to).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk());
	};

	@Test
	public void whenSendMailCodeFail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/code/mail").param("to", to).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().is5xxServerError());
	};
}
