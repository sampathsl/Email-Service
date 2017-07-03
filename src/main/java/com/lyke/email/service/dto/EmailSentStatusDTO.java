/**
 * Created by SAMPATH on 07/01/2017.
 * EmailSentStatus result data transfer class between client user and email service
 * emailSent - email sending status
 */

package com.lyke.email.service.dto;

import java.io.Serializable;

public class EmailSentStatusDTO implements Serializable {
	
	private static final long serialVersionUID = 65759789579575L;

	private String emailSent;
	
	public EmailSentStatusDTO(String emailSent) {
		super();
		this.emailSent = emailSent;
	}

	public String getEmailSent() {
		return emailSent;
	}

	public void setEmailSent(String emailSent) {
		this.emailSent = emailSent;
	}

}
