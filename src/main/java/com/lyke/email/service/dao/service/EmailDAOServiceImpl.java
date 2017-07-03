/**
 * Created by SAMPATH on 07/01/2017.
 */

package com.lyke.email.service.dao.service;

import java.util.Base64;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lyke.email.service.domain.EmailServiceAuth;

@Service("emailDAOService")
public class EmailDAOServiceImpl implements EmailDAOService {
	
	private static final Logger logger = LogManager.getLogger(EmailDAOServiceImpl.class);
	
	/**
	 * @author SAMPATH
	 * Implement the varifyAuth service with user credentials
	 */

	@Transactional
	@Override
	public boolean varifyAuth(EmailServiceAuth emailServiceAuth) {
		//TODO - not implemented database part
		// currently static check user name : test , key : c3Nzc3NzYWFhYWFhYTEyMzQ1
		try {
			if(emailServiceAuth.getUserName().equalsIgnoreCase("test") && 
					new String(Base64.getDecoder().decode(emailServiceAuth.getKey()), "utf-8").equalsIgnoreCase("ssssssaaaaaaa12345")) {
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return false;
	}

}
