/**
 * Created by SAMPATH on 07/01/2017.
 * Entity and data transfer object mapping class
 */

package com.lyke.email.service.util;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.lyke.email.service.domain.EmailData;
import com.lyke.email.service.domain.EmailServiceAuth;
import com.lyke.email.service.dto.EmailDTO;
import com.lyke.email.service.dto.EmailServiceAuthDTO;

@Component
public class EntityDTOMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Convert validation errors to ResponseEntity class
	 * @param errors
	 * @return ResponseEntity
	 */
	public ResponseEntity<List<CustomErrorType>> getErrors(Errors errors) {

		return ResponseEntity.badRequest().body(errors.getAllErrors().stream()
				.map(msg -> new CustomErrorType(msg.getDefaultMessage())).collect(Collectors.toList()));

	}
	
	/**
	 * Convert email entity to to email data transfer object
	 * @param email
	 * @return emailDTO
	 */
	public EmailDTO convertEmailToDto(EmailData email) {
		EmailDTO emailDTO = modelMapper.map(email, EmailDTO.class);
		return emailDTO;
	}

	/**
	 * Convert email data transfer object to email entity object
	 * @param emailDTO
	 * @return email
	 * @throws ParseException
	 */
	public EmailData convertEmailToEntity(EmailDTO emailDTO) throws ParseException {
		EmailData email = modelMapper.map(emailDTO, EmailData.class);
		return email;
	}

	/**
	 * Convert email service auth entity to email service auth data transfer object
	 * @param emailServiceAuth
	 * @return emailServiceAuthDTO
	 */
	public EmailServiceAuthDTO convertEmailServiceAuthToDto(EmailServiceAuth emailServiceAuth) {
		EmailServiceAuthDTO emailServiceAuthDTO = modelMapper.map(emailServiceAuth, EmailServiceAuthDTO.class);
		return emailServiceAuthDTO;
	}

	/**
	 * Convert email service auth data transfer object to email service auth entity object
	 * @param emailServiceAuthDTO
	 * @return emailServiceAuth
	 * @throws ParseException
	 */
	public EmailServiceAuth convertEmailServiceAuthToEntity(EmailServiceAuthDTO emailServiceAuthDTO)
			throws ParseException {
		EmailServiceAuth emailServiceAuth = modelMapper.map(emailServiceAuthDTO, EmailServiceAuth.class);
		return emailServiceAuth;
	}

}
