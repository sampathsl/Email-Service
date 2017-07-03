/**
 * Created by SAMPATH on 07/01/2017.
 */

package com.lyke.email.service.adapter;

import com.lyke.email.service.domain.EmailData;

/**
 * @author SAMPATH
 * Initiate the email service provider interface
 */

public interface EmailServiceProvider {

	public boolean sendEmail(EmailData email);

}
