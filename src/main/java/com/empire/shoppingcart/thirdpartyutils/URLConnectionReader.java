package com.empire.shoppingcart.thirdpartyutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLConnectionReader {

	private static final Logger log = LoggerFactory.getLogger(URLConnectionReader.class);

	public static void connectAndCreateErrors(String connectString) {
		
		log.info("Lets connect to an external Rest Service.");
		URLConnectionReader1.connectAndCreateErrors(connectString);
		URLConnectionReader2.connectAndCreateErrors(connectString);
		URLConnectionReader3.connectAndCreateErrors(connectString);
		URLConnectionReader4.connectAndCreateErrors(connectString);
		URLConnectionReader5.connectAndCreateErrors(connectString);
		URLConnectionReader6.connectAndCreateErrors(connectString);
		URLConnectionReader7.connectAndCreateErrors(connectString);
		URLConnectionReader8.connectAndCreateErrors(connectString);
		URLConnectionReader9.connectAndCreateErrors(connectString);
		URLConnectionReader10.connectAndCreateErrors(connectString);
		log.info("Connecting to an external rest service complete.");

	}
}