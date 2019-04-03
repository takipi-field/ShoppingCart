package com.shoppingcart.manager;

import java.util.Date;

import javax.security.auth.login.LoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.utils.DateUtils;
import com.mockdata.generate.utils.RandomUtil;
import com.shoppingcart.domain.Customer;
import com.shoppingcart.exception.ShoppingCartException;

public class LoginManager {

	private static final Logger log = LoggerFactory.getLogger(LoginManager.class);

	private Customer loggedInCustomer;
	private Date loggedInDateTime;
	
	public LoginManager(Customer customer) {
		this.loggedInCustomer = customer;
		this.loggedInDateTime = DateUtils.getNow();
		
	}

	public void login() {
		try {
			log.info("Attempting to login");
			int randomNumber = RandomUtil.generateRandom(10);
			if (randomNumber == 5) {
				loggedInDateTime = null;
				throw new LoginException("Unable to login for Customer: " + loggedInCustomer.getAccountNumber());
			}
			log.info("Login Successful for customer: {} at: {}", loggedInCustomer.getAccountNumber(), loggedInDateTime);
		} catch (LoginException e) {
			log.error("Login Exception", e);
			throw new ShoppingCartException(e);
		}
	}
}