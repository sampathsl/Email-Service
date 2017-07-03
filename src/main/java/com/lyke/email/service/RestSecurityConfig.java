/**
 * Created by SAMPATH on 07/02/2017.
 */

package com.lyke.email.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author SAMPATH
 * Configure spring security
 * If this API is used within the organization, implement the authorize requests
 */

@Configuration
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/api/v1/email-send/varify","/api/v1/email-send").permitAll();
		http.csrf().disable();
	}
	
}
