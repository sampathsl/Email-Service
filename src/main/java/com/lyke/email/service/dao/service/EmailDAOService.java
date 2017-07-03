/**
 * Created by SAMPATH on 07/01/2017.
 */

package com.lyke.email.service.dao.service;

import com.lyke.email.service.domain.EmailServiceAuth;

/**
 * @author SAMPATH
 * Initiate the email user DAO service
 */

public interface EmailDAOService {
	
	boolean varifyAuth(EmailServiceAuth emailServiceAuth);
	
}
