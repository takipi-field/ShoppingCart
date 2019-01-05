package com.shoppingcart.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main2RandomExceptions {

	private final static Logger log = LoggerFactory.getLogger(Main2RandomExceptions.class);

	public static void main(String[] args) {
		
		TwoRandomExceptions TwoRandomExceptions = new TwoRandomExceptions();
		log.info("Starting to create 2 random exceptions");
		TwoRandomExceptions.createTwoRandomExceptions();
		log.info("We are done with Random Exceptions");
	}	
}