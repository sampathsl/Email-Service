/**
 * Created by SAMPATH on 07/01/2017.
 */

package com.lyke.email.service;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lyke.email.service.util.UserSessionCleanUpScheduler;

/**
 * @author SAMPATH
 * Spring boot application start up class
 */

@SpringBootApplication
public class EmailServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
		new UserSessionCleanUpScheduler().run();
	}
	
	/**
	 * Register automatic model mapper bean
	 * @return new ModelMapper bean
	 */
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
}
