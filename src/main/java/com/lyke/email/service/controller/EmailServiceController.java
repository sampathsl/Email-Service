/**
 * Created by SAMPATH on 07/01/2017.
 */

package com.lyke.email.service.controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lyke.email.service.adapter.MailGunService;
import com.lyke.email.service.adapter.ManDrillService;
import com.lyke.email.service.adapter.SendGridService;
import com.lyke.email.service.adapter.ServiceProvider;
import com.lyke.email.service.dao.service.EmailDAOService;
import com.lyke.email.service.domain.EmailServiceAuth;
import com.lyke.email.service.dto.AuthCodeDTO;
import com.lyke.email.service.dto.EmailDTO;
import com.lyke.email.service.dto.EmailSentStatusDTO;
import com.lyke.email.service.dto.EmailServiceAuthDTO;
import com.lyke.email.service.util.CustomErrorType;
import com.lyke.email.service.util.EntityDTOMapper;
import com.lyke.email.service.util.SessionAuthCollector;

/**
 * @author SAMPATH
 * Implement the request mappings
 * Initial authorize the email sending clients using basic authentication
 * After the automatically generated code is used to access the mail sending service 
 */

@RestController
@RequestMapping("/api/v1")
public class EmailServiceController {

	private static final Logger logger = LogManager.getLogger(EmailServiceController.class);
	
	@Autowired
	EntityDTOMapper entityDTOMapper;
	
	@Autowired
	private EmailDAOService emailDAOService;
	
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> viewRoot() {
		logger.info("IN viewRoot METHOD");
		return new ResponseEntity<String>("Hello", HttpStatus.OK);
	}*/
	
	/**
	 * Verify client for email service access 
	 * @param emailServiceAuthDTO
	 * @param ucBuilder
	 * @param errors
	 * @return ResponseEntity
	 * @throws UnsupportedEncodingException
	 */

	@RequestMapping(value = "/email-send/varify", method = RequestMethod.POST)
	public ResponseEntity<?> sendEmailVarify(@Valid @RequestBody EmailServiceAuthDTO emailServiceAuthDTO,
			Errors errors) throws UnsupportedEncodingException {

		logger.info("IN sendEmailVarify METHOD");

		if (errors.hasErrors()) {
			return entityDTOMapper.getErrors(errors);
		}

		UUID uuid = null;

		try {
			// varify service authorization
			if (emailDAOService.varifyAuth(entityDTOMapper.convertEmailServiceAuthToEntity(emailServiceAuthDTO))) {
				// generate random auth code
				// save in memory | after use remove it , if not use remove items periodically
				uuid = UUID.randomUUID();
				SessionAuthCollector.emailServiceAuthList.put(uuid.toString(), new EmailServiceAuth(
						emailServiceAuthDTO.getUserName(), emailServiceAuthDTO.getKey(), new Date()));
			} else {
				return new ResponseEntity(new CustomErrorType("Access denied! Please recheck you user name and key."),
						HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity(new CustomErrorType("Error! Please recheck you user name and key."),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<AuthCodeDTO>(
				new AuthCodeDTO(Base64.getEncoder().encodeToString(uuid.toString().getBytes("utf-8"))), HttpStatus.OK);

	}
	
	/**
	 * Email sending service
	 * @param emailDTO
	 * @param ucBuilder
	 * @param errors
	 * @return ResponseEntity
	 */

	@RequestMapping(value = "/email-send", method = RequestMethod.POST)
	public ResponseEntity<?> sendEmail(@Valid @RequestBody EmailDTO emailDTO, UriComponentsBuilder ucBuilder,
			Errors errors) {

		logger.info("IN sendEmail METHOD");

		if (errors.hasErrors()) {
			return entityDTOMapper.getErrors(errors);
		}

		EmailServiceAuth emailServiceAuth = SessionAuthCollector.emailServiceAuthList
				.get(new String(Base64.getDecoder().decode(emailDTO.getAuthCode())));

		if (emailServiceAuth != null) {

			SessionAuthCollector.emailServiceAuthList
					.remove(new String(Base64.getDecoder().decode(emailDTO.getAuthCode())));
			try {

				if (new ServiceProvider(new MailGunService()).executeStrategy(entityDTOMapper.convertEmailToEntity(emailDTO))) {
					return new ResponseEntity<EmailSentStatusDTO>(new EmailSentStatusDTO("SUCCESS"), HttpStatus.OK);
				} else if (new ServiceProvider(new SendGridService()).executeStrategy(entityDTOMapper.convertEmailToEntity(emailDTO))) {
					return new ResponseEntity<EmailSentStatusDTO>(new EmailSentStatusDTO("SUCCESS"), HttpStatus.OK);
				} else if (new ServiceProvider(new ManDrillService()).executeStrategy(entityDTOMapper.convertEmailToEntity(emailDTO))) {
					return new ResponseEntity<EmailSentStatusDTO>(new EmailSentStatusDTO("SUCCESS"), HttpStatus.OK);
				} else {
					return new ResponseEntity<EmailSentStatusDTO>(new EmailSentStatusDTO("FAIL"), HttpStatus.OK);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
			
		return new ResponseEntity<EmailSentStatusDTO>(new EmailSentStatusDTO("SESSION_ERROR"), HttpStatus.OK);

	}

}
