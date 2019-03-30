package com.shoppingcart.manager;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.DelayGenerator;
import com.shoppingcart.dao.CustomerDAO;
import com.shoppingcart.dao.impl.CustomerDAOImpl;
import com.shoppingcart.domain.Customer;
import com.shoppingcart.domain.Order;

public class CustomerManager {

	private CustomerDAO customerDAOImpl = new CustomerDAOImpl();
	private static final Logger log = LoggerFactory.getLogger(CustomerManager.class);
			
	public Customer get(String customerNumber) {
		log.info("Getting a customer");
		return customerDAOImpl.getByCustomerNumber(customerNumber);
	}

	public Customer create(String customerNumber) {
		log.info("Creating a customer");
		DelayGenerator.generateRandomDelay();
		return customerDAOImpl.create(customerNumber);
	}

	public Customer create() {
		log.info("Creating a customer");
		DelayGenerator.generateRandomDelay();
		return customerDAOImpl.create();
	}

	public Customer update(Customer customer) {
		log.info("Updating customerNumber: {}", customer.getAccountNumber());
		DelayGenerator.generateRandomDelay();
		return customerDAOImpl.update(customer);
	}
	
	public Order getMostRecentOrder(String customerNumber) {
		log.info("GetMostRecent Order for a customerNumber {}", customerNumber);
		return null;
	}
	
	public List<Order> getAllOrders(String customerNumber) {
		log.info("GetAllOrders for customerNumber {}", customerNumber);
		return new ArrayList<>();
	}
	
	public boolean cancelOrder(String customerNumber, String orderNumber) {
		log.info("Cancel Order for customerNumber {} orderNumber {}", customerNumber, orderNumber);
		return false;
	}
	
	public boolean cancelOrderDetail(String customerNumber, 
			String orderNumber, String orderDetailNumber) {
		log.info("Cancel Order Detail for customerNumber, orderNumber, orderDetailNumber {} {} {}", customerNumber, orderNumber, orderDetailNumber);
		return false;
	}

	public boolean delete(Customer customer) {
		log.info("Delete for customer {}", customer.getAccountNumber());
		DelayGenerator.generateRandomDelay();
		return customerDAOImpl.delete(customer);
	}

	public Customer find(String customerNumber) {
		log.info("Find customerNumber");
		DelayGenerator.generateRandomDelay();
		return customerDAOImpl.find(customerNumber);
	}
}