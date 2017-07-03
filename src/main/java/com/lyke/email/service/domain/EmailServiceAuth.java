/**
 * Created by SAMPATH on 07/01/2017.
 * Email service authorization entity class
 * userName - user name
 * key - authorization key for access email service
 * creationDate - user creation date
 */

package com.lyke.email.service.domain;

import java.io.Serializable;
import java.util.Date;

public class EmailServiceAuth implements Serializable {

	private static final long serialVersionUID = 216516515456456L;

	private String userName;
	private String key;
	private Date creationDate;
	
	public EmailServiceAuth() {}

	public EmailServiceAuth(String userName, String key, Date creationDate) {
		this.userName = userName;
		this.key = key;
		this.creationDate = creationDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
