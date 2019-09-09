package com.empire.shoppingcart.thirdpartyutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empire.mockdata.generate.utils.RandomUtil;

public class URLConnectionReader10 {

	private static final Logger log = LoggerFactory.getLogger(URLConnectionReader10.class);

	public static void connectAndCreateErrors(String connectString) {
		//Fail 1 in 10 attempts - take 10 seconds to connect and fail.
		int randomNumber = RandomUtil.generateRandom(10);
		log.info("Trying to connect to RestAPI. Random Number is {}", randomNumber);
		if (randomNumber == 5) {
			log.error("Connecting to external Rest URL to generate 25 errors");
			connectAndCreateErrors(connectString, 25);
		} else {
			connectAndCreateErrors(connectString, randomNumber);			
		}
	}
	
    private static void connectAndCreateErrors(String connectString, int noOfErrors) {
    	BufferedReader in = null;
    	try {
	        URL url = new URL(connectString + "?noOfErrors=" + noOfErrors);
	        URLConnection yc = url.openConnection();
	        in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	            log.info(inputLine);
	        }
    	} catch (Exception e) {
    		log.error("An exception occured in calling an external Rest URL", e);
    	} finally {
    		if (in != null) {
    	        try {
					in.close();
				} catch (IOException e) {
		    		log.error("IOException", e);				}
    		}
    	}
    }
}