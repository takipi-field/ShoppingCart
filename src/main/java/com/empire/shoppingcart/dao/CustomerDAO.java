package com.empire.shoppingcart.dao;

import java.util.List;

import com.empire.shoppingcart.domain.Customer;
import com.empire.shoppingcart.domain.Order;

public interface CustomerDAO {

	public abstract Customer getByCustomerNumber(String customerNumber);
	public abstract Customer getById(int id);
	public abstract Customer create();
	public abstract Customer create(String customerNumber);	
	public abstract Customer update(Customer customer);
	public abstract boolean delete(Customer customer);
	public abstract List<Order> getAllOrders(String customerNumber);
	public abstract Customer find(String customerNumber);
}
