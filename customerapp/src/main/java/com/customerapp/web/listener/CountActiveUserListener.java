package com.customerapp.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;


/**
 * Application Lifecycle Listener implementation class CountActiveUserListener
 *
 */
@WebListener
public class CountActiveUserListener implements HttpSessionListener {

	Logger logger=Logger.getLogger(CountActiveUserListener.class);
	private static int counter=0;
    /**
     * Default constructor. 
     */
    public CountActiveUserListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	counter++;
    	logger.debug("Total active sessions : "+counter);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	counter--;
    	logger.debug("Total active sessions : "+counter);
    }
	
}
