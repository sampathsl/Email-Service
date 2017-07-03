/**
 * Created by SAMPATH on 07/01/2017.
 * Initiate the email service authorized user sessions
 */

package com.lyke.email.service.util;

import java.util.concurrent.ConcurrentSkipListMap;

import com.lyke.email.service.domain.EmailServiceAuth;

public class SessionAuthCollector {
	
	public static volatile ConcurrentSkipListMap<String,EmailServiceAuth> emailServiceAuthList = new ConcurrentSkipListMap<>();
	
}
