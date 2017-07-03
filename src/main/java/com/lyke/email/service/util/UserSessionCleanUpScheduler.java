/**
 * Created by SAMPATH on 07/01/2017.
 * User session clean up scheduler service
 */

package com.lyke.email.service.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class UserSessionCleanUpScheduler extends TimerTask {

    private final static Timer timer = new Timer();

    /**
     * Remove unused expired sessions from the memory in every 30 minutes
     */
    
    long DELAY = 1800000;
    
    public void cleanUnusedSessions() {
    	SessionAuthCollector
    	.emailServiceAuthList
    	.entrySet()
    	.removeIf(entry -> entry.getValue().getCreationDate().getTime() < new Date().getTime() - DELAY);
    }

    @Override
    public void run() {
        timer.schedule(new UserSessionCleanUpScheduler(),DELAY);
        cleanUnusedSessions();
    }
	
}
