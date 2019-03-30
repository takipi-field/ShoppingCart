package com.shoppingcart.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingCartPropertyReader {

	private static final Logger log = 
		LoggerFactory.getLogger(ShoppingCartPropertyReader.class);

	private static Properties prop = null;
	private static final String PROPERTIESFILE = "/ShoppingCart.properties";
	
	//Private Constructor to avoid initiation
	private ShoppingCartPropertyReader() {
    }

	public static Properties getInstance() {
		if (prop == null) {
			init();
		}
		return prop;
	}

	private static void init() {
		InputStream inStream = null;
		try {
			log.info("Loading properties from file: {}", PROPERTIESFILE);
			inStream = ShoppingCartPropertyReader.class.getResourceAsStream(PROPERTIESFILE);
			prop = new Properties();
			log.info("Now lets load the properties");
			prop.load(inStream);
		} catch (Exception e) {
			log.error("Exception in thread: {}", e);
			log.error(e.getMessage());
		}
		finally {
			try {
				if (inStream != null) {
					log.info("Closing the input Stream");
					inStream.close();
				}
			} catch (Exception e) {
				log.error("An error occured: {}", e.getMessage());
			}
		}
	}
}