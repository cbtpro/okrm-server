package com.useful_person.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void steup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void user() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/user").param("username", "jojo")
					.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void whenGetUserInfoSuccess() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/user/ff9c7f59-3652-4510-bd0b-1d57d2fd3917").contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.nickname").value("tom"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void whenGetUserInfoFail() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/user/-1").contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(MockMvcResultMatchers.status().is4xxClientError());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void task() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/tasks").contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
