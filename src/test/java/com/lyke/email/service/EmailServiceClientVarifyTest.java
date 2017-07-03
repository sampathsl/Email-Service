package com.lyke.email.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyke.email.service.domain.EmailServiceAuth;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmailServiceClientVarifyTest {

	private ObjectMapper objectMapper = new ObjectMapper();
	private final String URI = "/api/v1/email-send/varify";

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
				.content(objectMapper.writeValueAsString(getSampleClientEmailServiceAuthDTO()))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	private EmailServiceAuth getSampleClientEmailServiceAuthDTO() {
		EmailServiceAuth emailServiceAuth = new EmailServiceAuth();
		emailServiceAuth.setUserName("test");
		emailServiceAuth.setKey("c3Nzc3NzYWFhYWFhYTEyMzQ1");
		emailServiceAuth.setCreationDate(new Date());
		return emailServiceAuth;
	}

}
