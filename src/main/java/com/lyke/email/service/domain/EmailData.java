/**
 * Created by SAMPATH on 07/01/2017.
 * Email data entity class
 * authCode - auto generated email service access authorization code
 * to - List of sending to e-mail addresses
 * cc - List of sending cc e-mail addresses
 * bcc - List of sending bcc e-mail addresses
 * subject - Email subject
 * message - e-mail content
 */

package com.lyke.email.service.domain;

import java.io.Serializable;
import java.util.List;


public class EmailData implements Serializable {

	private static final long serialVersionUID = 56156454654655L;

	private String authCode;
	private List<String> to;
	private List<String> cc;
	private List<String> bcc;
	/*private String mimeType;*/
	private String subject;
	private String message;

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
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

}
