package com.lyke.email.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lyke.email.service.dto.AuthCodeDTO;
import com.lyke.email.service.dto.EmailDTO;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmailServiceSendEmailTest {

	private ObjectMapper objectMapper = new ObjectMapper();
	private final String URI1 = "/api/v1/email-send/verify";
	private final String URI2 = "/api/v1/email-send";

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

		MvcResult result = this.mockMvc.perform(post(URI1).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new SampleEmailServiceAuthDTO().getEmailServiceAuth()))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		Gson gson = new GsonBuilder().create();
		AuthCodeDTO authCodeDTO = gson.fromJson(result.getResponse().getContentAsString(), AuthCodeDTO.class);

		this.mockMvc.perform(post(URI2).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(
						getSampleClientEmail(new String(authCodeDTO.getAuthCode().getBytes(), "UTF-8"))))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	private EmailDTO getSampleClientEmail(String authCode) {
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setAuthCode(authCode);
		emailDTO.setTo(Arrays.asList("itsampathsl@gmail.com"));
		emailDTO.setCc(new ArrayList<>());
		emailDTO.setBcc(new ArrayList<>());
		emailDTO.setSubject("TEST SUBJECT");
		emailDTO.setMessage("TEST MESSAGE TEST MESSAGE");
		return emailDTO;
	}

}
