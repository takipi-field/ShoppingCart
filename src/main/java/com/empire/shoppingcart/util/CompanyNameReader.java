package com.empire.shoppingcart.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyNameReader {

	private static final Logger log = 
		LoggerFactory.getLogger(CompanyNameReader.class);

	private static Properties properties = null;
	private static final String PROPFILE = "/CompanyNames.properties";
	
	//Private Constructor to avoid initiation
	private CompanyNameReader() {
    }

	public static Properties getInstance() {
		if (properties == null) {
			init();
		}
		return properties;
	}

	private static void init() {
		InputStream inputStream = null;
		try {
			log.debug("Loading properties from file: " + PROPFILE);
			inputStream = CompanyNameReader.class.getResourceAsStream(PROPFILE);
			properties = new Properties();
			properties.load(inputStream);
		} catch (Exception e) {
			log.error("An exception occured: {}", e);
		}
		finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				log.error("An exception occured {}", e.getMessage());
			}
		}
	}
}