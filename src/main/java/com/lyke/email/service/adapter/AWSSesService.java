/**
 * Created by SAMPATH on 07/04/2017.
 */

package com.lyke.email.service.adapter;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.lyke.email.service.domain.EmailData;

/**
 * @author SAMPATH Implement the AWS SES email service provider
 */

public class AWSSesService implements EmailServiceProvider {

	private static final Logger logger = LogManager.getLogger(AWSSesService.class);

	@Override
	public boolean sendEmail(EmailData email) {

		Properties props = new Properties();
		props.put("mail.smtp.host", ""); //TODO Add your AWS host here
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication("", "");//TODO add your AWS SES credentials
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("")); //TODO add your from email address
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", email.getTo())));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(email.getCc().size() > 0 ? String.join(",", email.getCc()) : ""));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(email.getBcc().size() > 0 ? String.join(",", email.getBcc()) : ""));
			message.setSubject(email.getSubject());
			message.setText(email.getMessage());
			Transport.send(message);
			logger.info("AWS SES EMAIL SENT");
			return true;
			
		} catch (MessagingException e) {
			logger.error(e.getMessage());
			return false;
		}

	}

}
