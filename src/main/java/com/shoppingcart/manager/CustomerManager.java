package com.shoppingcart.manager;

import java.util.List;

import com.shoppingcart.dao.CustomerDAO;
import com.shoppingcart.dao.impl.CustomerDAOImpl;
import com.shoppingcart.domain.Customer;
import com.shoppingcart.domain.Order;

public class CustomerManager {

	private CustomerDAO customerDAOImpl = new CustomerDAOImpl();
			
	public Customer get(String customerNumber) {
		return customerDAOImpl.getByCustomerNumber(customerNumber);
	}

	public Customer create(String customerNumber) {
		return customerDAOImpl.create(customerNumber);
	}

	public Customer create() {
		return customerDAOImpl.create();
	}

	public Customer update(Customer customer) {
		return customerDAOImpl.update(customer);
	}
	
	public Order getMostRecentOrder(String customerNumber) {
		return null;
	}
	
	public List<Order> getAllOrders(String customerNumber) {
		return null;
	}
	
	public boolean cancelOrder(String customerNumber, String orderNumber) {
		return false;
	}
	
	public boolean cancelOrderDetail(String customerNumber, String orderNumber, String orderDetailNumber) {
		return false;
	}

	public boolean delete(Customer customer) {
		return customerDAOImpl.delete(customer);
		
	}

	public Customer find(String customerNumber) {
		return customerDAOImpl.find(customerNumber);
	}
}