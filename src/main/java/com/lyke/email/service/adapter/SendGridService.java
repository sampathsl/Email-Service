/**
 * Created by SAMPATH on 07/01/2017.
 */

package com.lyke.email.service.adapter;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.lyke.email.service.domain.EmailData;
import com.lyke.email.service.util.EmailContent;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

/**
 * @author SAMPATH
 * Implement the SendGrid email service provider
 */

public class SendGridService implements EmailServiceProvider {

	private static final Logger logger = LogManager.getLogger(SendGridService.class);

	@Override
	public boolean sendEmail(EmailData email) {

		Email from = new Email("email@sameemailprovider.com");
		String subject = email.getSubject();
		Email to = new Email(String.join(",", email.getTo()));
		
		Content content = null;
		if (EmailContent.getEmailContentType(email.getMessage()).equalsIgnoreCase("text/html")) {
			content = new Content("text/html", email.getMessage());
		} else if (EmailContent.getEmailContentType(email.getMessage()).equalsIgnoreCase("text/plain")) {
			content = new Content("text/plain", email.getMessage());
		} else {
			// default set
			content = new Content("text/plain", email.getMessage());
		}

		// no cc , bcc setters
		email.getCc().forEach(e -> {
			to.setEmail(e);
		});
		email.getBcc().forEach(e -> {
			to.setEmail(e);
		});

		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid("");//TODO add key here SG.xxxxx
		Request request = new Request();

		try {

			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);

			if (response.getStatusCode() != 202) {
				return false;
			}

			logger.info("SendGrid SEND EMAIL END");

		} catch (IOException ex) {
			logger.error(ex.getMessage());
			return false;
		}

		return true;

	}

}
