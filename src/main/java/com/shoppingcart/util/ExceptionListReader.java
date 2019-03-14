package com.shoppingcart.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.exception.ShoppingCartException;

public class ExceptionListReader {

	private final static Logger logger = 
		LoggerFactory.getLogger(ExceptionListReader.class);

	private static Properties properties = null;
	private static final String propertiesFile = "/ExceptionList.properties";
	
	//Private Constructor to avoid initiation
	private ExceptionListReader() {
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
			inputStream = ExceptionListReader.class.getResourceAsStream(propertiesFile);
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