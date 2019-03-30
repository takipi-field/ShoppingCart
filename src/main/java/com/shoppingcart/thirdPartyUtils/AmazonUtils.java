package com.shoppingcart.thirdpartyutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.domain.SKU;
import com.shoppingcart.exception.ShoppingCartException;

public class AmazonUtils {

	private static boolean connectionFailed = true;
	private static final Logger log = LoggerFactory.getLogger(AmazonUtils.class);

	
	private AmazonUtils() {
		//Do nothing
	}
	
	public static String connect() {
		log.info("Trying to connect to Amazon Utils");
		return "http://app.tapiki.com:8080/app/download?t=inst";
	}

	public static String getSkuType(String connection, SKU sku) {
		if (connectionFailed) {
			throw new ShoppingCartException("Unable to connect to third party server to getSkuType." + connection);
		}
		return sku.getSkuType();
	}
}
