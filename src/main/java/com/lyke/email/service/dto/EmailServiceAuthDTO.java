/**
 * Created by SAMPATH on 07/01/2017.
 * Email service auth result data transfer class between client user and email service
 * userName - Email service access client user name
 * key - Email service access client user key
 */

package com.lyke.email.service.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class EmailServiceAuthDTO implements Serializable {
	
	private static final long serialVersionUID = 31564564564455L;

	@NotEmpty(message = "User Name can''t be empty!")
	@Size(min = 4, max = 200)
	private String userName;
	
	@NotEmpty(message = "Key can''t be empty!")
	@Size(min = 20, max = 200)
	private String key;

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

	@Override
	public String toString() {
		return "EmailServiceAuthDTO [userName=" + userName + ", key=" + key + "]";
	}

}
