package com.useful.person.core.controller;

import java.util.Date;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author peter
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void steup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void whenQueryUsersSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user").param("username", "tom")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").value(true));

	}

//	@Test
//	public void whenGetUserInfoSuccess() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/user/2c948a896d054a7f016d054a90530000")
//				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.nickname").value("tom"));
//	}

	@Test
	public void whenGetUserInfoFail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/-1").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	public void whenCreateUserSuccess() throws Exception {
		Date date = new Date();
		
		String userContent = "{\"username\": \"tom\", \"nickname\": \"tom\", \"password\": \"123456\", \"birthday\": " + date.getTime() + "}";
		mockMvc.perform(
				MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(userContent))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.uuid").isNotEmpty());
	}

	@Test
	public void whenCreateUserFail() {
	}

}
