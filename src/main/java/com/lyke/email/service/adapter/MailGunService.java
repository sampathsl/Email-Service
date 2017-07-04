/**
 * Created by SAMPATH on 07/01/2017.
 */

package com.lyke.email.service.adapter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.lyke.email.service.domain.EmailData;
import com.lyke.email.service.util.EmailContent;

import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.Mail;
import net.sargue.mailgun.Response;
import net.sargue.mailgun.content.Body;

/**
 * @author SAMPATH
 * Implement the MailGun email service provider
 */

public class MailGunService implements EmailServiceProvider {
	
	private static final Logger logger = LogManager.getLogger(MailGunService.class);

	@Override
	public boolean sendEmail(EmailData email) {
		try {
			
			Configuration configuration = new Configuration()
					.domain("") //TODO sandbox456454....
				    .apiKey("") //TODO key-xxxxx
				    .from("Test Account","test@testwork.com");
			
			Response response = null;
			
			if (EmailContent.getEmailContentType(email.getMessage()).equalsIgnoreCase("text/html")) {
				
				if (email.getCc() != null && email.getCc().size() > 0 && email.getBcc() != null && email.getBcc().size() > 0) {
					response = Mail.using(configuration)
						    .to(String.join(",",email.getTo()))
						    .cc(String.join(",",email.getCc()))
						    .bcc(String.join(",",email.getBcc()))
						    .subject(email.getSubject())
						    .html(email.getMessage())
						    .build()
						    .send();
				} else if (email.getCc() != null && email.getBcc().size() > 0) {
					response = Mail.using(configuration)
						    .to(String.join(",",email.getTo()))
						    .cc(String.join(",",email.getCc()))
						    .subject(email.getSubject())
						    .html(email.getMessage())
						    .build()
						    .send();
				} else if (email.getBcc() != null && email.getBcc().size() > 0) {
					response = Mail.using(configuration)
						    .to(String.join(",",email.getTo()))
						    .bcc(String.join(",",email.getBcc()))
						    .subject(email.getSubject())
						    .html(email.getMessage())
						    .build()
						    .send();
				} else {
					response = Mail.using(configuration)
						    .to(String.join(",",email.getTo()))
						    .subject(email.getSubject())
						    .html(email.getMessage())
						    .build()
						    .send();
				}
				
			} else if (EmailContent.getEmailContentType(email.getMessage()).equalsIgnoreCase("text/plain")) {
				Body body = Body.builder()
				        .p(email.getMessage())
				        .build();
				
				if (email.getCc() != null && email.getCc().size() > 0 && email.getBcc() != null && email.getBcc().size() > 0) {
					response = Mail.using(configuration)
						    .to(String.join(",",email.getTo()))
						    .cc(String.join(",",email.getCc()))
						    .bcc(String.join(",",email.getBcc()))
						    .subject(email.getSubject())
						    .content(body)
						    .build()
						    .send();
				} else if (email.getCc() != null && email.getBcc().size() > 0) {
					response = Mail.using(configuration)
						    .to(String.join(",",email.getTo()))
						    .cc(String.join(",",email.getCc()))
						    .subject(email.getSubject())
						    .content(body)
						    .build()
						    .send();
				} else if (email.getBcc() != null && email.getBcc().size() > 0) {
					response = Mail.using(configuration)
						    .to(String.join(",",email.getTo()))
						    .bcc(String.join(",",email.getBcc()))
						    .subject(email.getSubject())
						    .content(body)
						    .build()
						    .send();
				} else {
					response = Mail.using(configuration)
						    .to(String.join(",",email.getTo()))
						    .subject(email.getSubject())
						    .content(body)
						    .build()
						    .send();
				}
				
			}
			
			if( response == null || (response != null && !response.isOk()) ) {
				return false;
			}
			
			logger.info("MAIL GUN EMAIL SENT");
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

}