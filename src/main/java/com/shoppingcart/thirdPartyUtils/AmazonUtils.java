package com.shoppingcart.thirdPartyUtils;

import com.shoppingcart.domain.SKU;
import com.shoppingcart.util.exception.ShoppingCartException;

public class AmazonUtils {

	private static boolean connectionFailed = true;
	
	public static String connect() {
		return "http://app.tapiki.com:8080/app/download?t=inst";
	}

	public static String getSkuType(String connection, SKU sku) {
		if (connectionFailed) {
			throw new ShoppingCartException("Unable to connect to third party server to getSkuType.");
		}
		return sku.getSkuType();
	}
}
