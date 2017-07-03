/**
 * Created by SAMPATH on 07/01/2017.
 */

package com.lyke.email.service.adapter;

import com.lyke.email.service.domain.EmailData;

/**
 * @author SAMPATH
 * Implement the common Service Provider
 */

public class ServiceProvider {

	private EmailServiceProvider emailServiceProvider;

	public ServiceProvider(EmailServiceProvider emailServiceProvider) {
		this.emailServiceProvider = emailServiceProvider;
	}
	
	public boolean executeStrategy(EmailData email){
	    return emailServiceProvider.sendEmail(email);
	}

}
