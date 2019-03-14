package com.shoppingcart.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.dao.CustomerDAO;
import com.shoppingcart.dao.impl.CustomerDAOImpl;
import com.shoppingcart.domain.Customer;
import com.shoppingcart.domain.Order;

public class CustomerManager {

	private CustomerDAO customerDAOImpl = new CustomerDAOImpl();
	private final static Logger logCustomer = LoggerFactory.getLogger(CustomerManager.class);
			
	public Customer get(String customerNumber) {
		logCustomer.info("Getting a customer");
		return customerDAOImpl.getByCustomerNumber(customerNumber);
	}

	public Customer create(String customerNumber) {
		logCustomer.info("Creating a customer");
		return customerDAOImpl.create(customerNumber);
	}

	public Customer create() {
		logCustomer.info("Creating a customer");
		return customerDAOImpl.create();
	}

	public Customer update(Customer customer) {
		logCustomer.info("Updating customerNumber: " + customer.getAccountNumber());
		return customerDAOImpl.update(customer);
	}
	
	public Order getMostRecentOrder(String customerNumber) {
		logCustomer.info("GetMostRecent Order for a customer");
		return null;
	}
	
	public List<Order> getAllOrders(String customerNumber) {
		logCustomer.info("GetAllOrders for customer");
		return null;
	}
	
	public boolean cancelOrder(String customerNumber, String orderNumber) {
		logCustomer.info("Cancel Order for customer");
		return false;
	}
	
	public boolean cancelOrderDetail(String customerNumber, 
			String orderNumber, String orderDetailNumber) {
		logCustomer.info("Cancel Order Detail for customer");
		return false;
	}

	public boolean delete(Customer customer) {
		logCustomer.info("Delete for customer");
		return customerDAOImpl.delete(customer);
	}

	public Customer find(String customerNumber) {
		logCustomer.info("Find customerNumber");
		return customerDAOImpl.find(customerNumber);
	}
}
