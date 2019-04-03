package com.shoppingcart.thirdpartyutils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.utils.RandomUtil;
import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.SKU;
import com.shoppingcart.exception.ShoppingCartException;

public class AmazonUtils {

	private static boolean connectionFailed = true;
	private static final Logger log = LoggerFactory.getLogger(AmazonUtils.class);

	
	private AmazonUtils() {
		//Do nothing
	}
	
	public static void connect() {		
		//Fail 1 in 10 attempts - take 10 seconds to connect and fail.
		int randomNumber = RandomUtil.generateRandom(10);
		log.info("Trying to connect to Amazon Utils. Random Number is {}", randomNumber);
		if (randomNumber == 5) {
			String ipAddress = "72.30.35.9";
			int port = 8080;
			Socket socket = null;
			try {
				socket = new Socket();
				socket.connect(new InetSocketAddress(ipAddress, port), 10000);
				log.info("Connection Successfull");
			} catch (IOException e) {
				log.error("IO socket timeout exception", e);
			} finally {
				try {
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					log.error("IO socket close exception", e);
				}
			}
		}
	}

	public static String getSkuType(SKU sku) {
		if (connectionFailed) {
			throw new ShoppingCartException("Unable to connect to third party server to getSkuType.");
		}
		return sku.getSkuType();
	}

	public static void validateProduct(Product product) {
		log.info("Sucessfully validated product in Amazon {}", product.getProductName());
	}
}