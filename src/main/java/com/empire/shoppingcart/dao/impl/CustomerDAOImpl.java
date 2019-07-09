package com.empire.shoppingcart.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empire.mockdata.generate.DataGenerator;
import com.empire.shoppingcart.dao.CustomerDAO;
import com.empire.shoppingcart.domain.Customer;
import com.empire.shoppingcart.domain.Order;
import com.empire.shoppingcart.exception.ShoppingCartException;
import com.empire.shoppingcart.persist.PersistLayer;

public class CustomerDAOImpl implements CustomerDAO {

	private DataGenerator dataGenerator = new DataGenerator();
	private PersistLayer persistLayer = new PersistLayer();
	private static final Logger log = LoggerFactory.getLogger(CustomerDAOImpl.class);


	@Override
	public Customer getByCustomerNumber(String customerNumber) {
		try {
			log.info("Trying to get the Order by customerNumber");
			Customer customer = dataGenerator.generateCustomer(customerNumber);
			log.info("Found the customer: {}", customer.getAccountNumber());
			return customer;
		} catch (Exception e) {
			log.error("Unable to get customer - getByCustomerNumber: {}", customerNumber);
			throw new ShoppingCartException(e);
		}
	}

	public Customer create(String customerNumber) {
		try {
			log.info("Creating the customer with CustomerNumber {}", customerNumber);
			Customer customer = dataGenerator.createCustomer(customerNumber);
			log.info("Completed creating the customer with CustomerNumber {}", customerNumber);
            return customer;
		} catch (Exception e) {
			log.error("Unable to create customer using customerNumber: {}", customerNumber, e);
		}
		throw new ShoppingCartException("Unable to create customer with CustomerNumber " + customerNumber);
	}

	@Override
	public Customer create() {
		try {
			log.info("Creating a customer");
			return dataGenerator.createCustomer1();
		} catch (Exception e) {
			log.error("Unable to create customer", e);
		}
		throw new ShoppingCartException("Unable to create customer");
	}


	@Override
	public Customer update(Customer customer) {
		log.info("Updating Customer");
		boolean success = persistLayer.persist(customer);
		if (success)
			return customer;
		throw new ShoppingCartException("Unable to persist customer " + customer.getAccountNumber());
	}

	@Override
	public boolean delete(Customer customer) {
		log.info("Deleting Customer");
		boolean success = persistLayer.persist(customer);
		if (success)
			return true;
		throw new ShoppingCartException("Unable to delete customer " + customer.getAccountNumber());
	}

	@Override
	public List<Order> getAllOrders(String customerNumber) {
		log.info("Getting All Orders by customerNumber: {}", customerNumber);
		// NeedToDo - complete this method
		return new ArrayList<>();
	}

	@Override
	public Customer getById(int id) {
		log.info("Getting All Orders by ID: {}", id);
		// NeedToDo - complete this method
		return null;
	}

	@Override
	public Customer find(String customerNumber) {
		log.info("Finding All Orders by customerNumber: {}", customerNumber);
		// NeedToDo - complete this method
		return null;
	}
}
