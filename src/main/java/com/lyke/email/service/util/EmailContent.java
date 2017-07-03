/**
 * Created by SAMPATH on 07/01/2017.
 * Email data content type verification
 */

package com.lyke.email.service.util;

public class EmailContent {

	public static String EMAIL_CONTENT_PATTERN = ".*\\<[^>]+>.*";
	
	/**
	 * Check the email content type - check whether email has HTML elements 
	 * @param emailContent
	 * @return content MIME type
	 */
	
	public static String getEmailContentType(String emailContent) {
		if(emailContent != null && emailContent.matches(EmailContent.EMAIL_CONTENT_PATTERN)) {
			return "text/html";
		} else {
			return "text/plain";
		}
	}

}
