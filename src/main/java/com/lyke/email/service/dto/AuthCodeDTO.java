/**
 * Created by SAMPATH on 07/01/2017.
 * Authorization code data transfer class between client user and email service
 * authCode - authorization code
 */

package com.lyke.email.service.dto;

import java.io.Serializable;

public class AuthCodeDTO implements Serializable {

	private static final long serialVersionUID = 15616548484894L;
	private String authCode;
	
	public AuthCodeDTO() {}

	public AuthCodeDTO(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

}
