/**
 * Created by SAMPATH on 07/01/2017.
 * Email data transfer class between client user and email service
 * authCode - auto generated email service access authorization code
 * to - List of sending to e-mail addresses
 * cc - List of sending cc e-mail addresses
 * bcc - List of sending bcc e-mail addresses
 * subject - Email subject
 * message - e-mail content
 */

package com.lyke.email.service.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 5545512118451221L;

	@NotEmpty(message = "AuthCode can''t be empty!")
	@Size(min = 20, max = 200)
	private String authCode;

	@NotEmpty(message = "Please provide at least one email to address!")
	private List<String> to;

	private List<String> cc;

	private List<String> bcc;

	/*@NotEmpty(message = "Mime type can''t be empty!")
	private String mimeType;*/

	@NotEmpty(message = "Subject can''t be empty!")
	private String subject;

	@NotEmpty(message = "Message can''t be empty!")
	private String message;

	public String getAuthCode() {
		return authCode;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public List<String> getBcc() {
		return bcc;
	}

	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}

	/*public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}*/

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

}
