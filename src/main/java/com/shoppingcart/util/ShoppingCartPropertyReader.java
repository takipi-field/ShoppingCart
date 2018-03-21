package com.shoppingcart.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.util.exception.ShoppingCartException;

public class ShoppingCartPropertyReader {

	private final static Logger logger = 
		LoggerFactory.getLogger(ShoppingCartPropertyReader.class);

	private static Properties properties = null;
	private static final String propertiesFile = "/ShoppingCart.properties";
	
	//Private Constructor to avoid initiation
	private ShoppingCartPropertyReader() {
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
			logger.info("Loading properties from file: " + propertiesFile);
			inputStream = ShoppingCartPropertyReader.class.getResourceAsStream(propertiesFile);
			properties = new Properties();
			properties.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShoppingCartException(e);
		}
		finally {
			try {
				inputStream.close();
			} catch (Exception e) {
				throw new ShoppingCartException(e);
			}
		}
	}
}