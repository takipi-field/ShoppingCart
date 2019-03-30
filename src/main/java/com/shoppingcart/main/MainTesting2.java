package com.shoppingcart.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTesting2 {

	public static final Logger log = LoggerFactory.getLogger(MainTesting2.class);

	public static void main(String[] args) {
		
		String x = "Hello World";
		int y = 100;
		log.info("This is a test log {} {}", x, y);
	}
}