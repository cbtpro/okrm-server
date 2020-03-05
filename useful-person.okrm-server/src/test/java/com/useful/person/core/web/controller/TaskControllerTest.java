package com.useful.person.core.web.controller;

import java.io.UnsupportedEncodingException;

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

/**
 * 
 * @author peter
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void steup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void whenCreateTaskSuccess() throws UnsupportedEncodingException, Exception {
		String content = "{\"title\": \"走西口\"}";
		String result = mockMvc
				.perform(MockMvcRequestBuilders.post("/task").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(content))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.uuid").isNotEmpty()).andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);
	}

	@Test
	public void whenUpdateTaskSuccess() throws Exception {
		String content = "{\"title\": \"走西口\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/task/ff9c7f59-3652-4510-bd0b-1d57d2fd3917").content(content)
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.updateTime").isNotEmpty());
	}

	@Test
	public void whenQueryTaskSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/task/ff9c7f59-3652-4510-bd0b-1d57d2fd3917").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.uuid").isNotEmpty());
	}

	@Test
	public void whenQueryTasksSuccess() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/tasks").param("title", "弹钢琴").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
	}

	@Test
	public void whenDeleteTaskSuccess() throws Exception {
	}

	@Test
	public void whenDeleteTasksSuccess() throws Exception {
	}
}
