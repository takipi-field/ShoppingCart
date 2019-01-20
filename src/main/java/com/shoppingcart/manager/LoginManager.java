package com.shoppingcart.manager;

import java.util.Date;

import javax.security.auth.login.LoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockData.generate.utils.DateUtils;
import com.mockData.generate.utils.RandomUtil;
import com.shoppingcart.domain.Customer;
import com.shoppingcart.util.ExceptionUtils;
import com.shoppingcart.util.exception.ShoppingCartException;

public class LoginManager {

	private final static Logger log = LoggerFactory.getLogger(LoginManager.class);

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
				throw new LoginException("Unable to login for Customer: " + loggedInCustomer.getAccountNumber());
			}
			log.info("Login Successful for customer: " + loggedInCustomer.getAccountNumber() + " at: " + loggedInDateTime);
		} catch (LoginException e) {
			log.error(ExceptionUtils.convertStackTraceToString(e));
			loggedInDateTime = null;
			throw new ShoppingCartException(e);
		}
	}

}
