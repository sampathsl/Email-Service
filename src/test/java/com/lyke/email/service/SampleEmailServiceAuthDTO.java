package com.lyke.email.service;

import com.lyke.email.service.domain.EmailServiceAuth;

import java.util.Date;

/**
 * Created by SAMPATH on 7/7/2017.
 */

public class SampleEmailServiceAuthDTO {

    public EmailServiceAuth getEmailServiceAuth() {
        return this.getSampleClientEmailServiceAuthDTO();
    }

    /**
     * Generate sample email service access auth
     * @return EmailServiceAuth
     */
    private EmailServiceAuth getSampleClientEmailServiceAuthDTO() {
        EmailServiceAuth emailServiceAuth = new EmailServiceAuth();
        emailServiceAuth.setUserName("test");
        emailServiceAuth.setKey("c3Nzc3NzYWFhYWFhYTEyMzQ1");
        emailServiceAuth.setCreationDate(new Date());
        return emailServiceAuth;
    }

}
