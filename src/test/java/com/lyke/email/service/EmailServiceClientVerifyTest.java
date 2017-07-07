package com.lyke.email.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmailServiceClientVerifyTest {

	private ObjectMapper objectMapper = new ObjectMapper();
	private final String URI = "/api/v1/email-send/verify";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).dispatchOptions(true).build();
	}

	@Test
	public void adminCanCreateOrganization() throws Exception {

		this.mockMvc.perform(post(URI).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new SampleEmailServiceAuthDTO().getEmailServiceAuth()))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
