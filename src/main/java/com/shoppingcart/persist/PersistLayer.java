package com.shoppingcart.persist;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.utils.RandomUtil;
import com.shoppingcart.domain.Customer;
import com.shoppingcart.domain.Order;
import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.SKU;
import com.shoppingcart.util.exception.SQLException;
import com.shoppingcart.util.exception.SkuException;

public class PersistLayer {

	private static final Logger log = LoggerFactory.getLogger(PersistLayer.class);

	public boolean persist(Order order) {
		try {
			log.info("Lets persist the Order");
			int randomNumber = RandomUtil.generateRandom(10);
			if (randomNumber == 5) {
				throw new SQLException("Unable to persist Order: " + order.getOrderNumber());
			}
			return true;
		} catch (SQLException e) {
			log.error("SQL 	Exception: {}", e.getMessage());
			return false;
		}
	}
	
	public void updateOrderDate(Order order, String dateStr) throws ParseException {
		log.info("Lets update the Order");
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = format.parse(dateStr);
		order.setLastUpdated(date);
		log.info("Completed updating the Order");
	}
	
	public boolean updateOrder(Order order) {
		log.info("Lets update the Order {}", order.getId());
		log.info("Sucessfully updated Order");
		return true;
	}

	public boolean persist(Product product) {
		log.info("Lets persist the product");
		int randomNumber = RandomUtil.generateRandom(10);
		if (randomNumber == 5) {
			throw new SQLException("Unable to persist product: " + product.getProductNumber());
		}
		log.info("Completed Persisting product");
		return true;
	}

	public boolean persistSku(SKU sku) {
		try {
			log.info("Lets persist the Sku");
			int randomNumber = RandomUtil.generateRandom(10);
			if (randomNumber == 8) {
				throw new SQLException("Unable to persist Sku: " + sku.getSkuNumber());
			}
			log.info("Completed Persisting Sku");
			return true;
		} catch (SQLException e) {
			//Swallow exception ...
			return false;
		}
	}
	
	public boolean deleteSku(SKU sku) {
		log.info("Deleting the Sku");
		int randomNumber = RandomUtil.generateRandom(101);
		if (randomNumber == 99) {
			throw new SkuException("Unable to delete Sku: " + sku.getSkuNumber());
		}
		log.info("Completed deleting Sku");
		return true;
	}

	public boolean persist(Customer customer) {
		try {
			log.info("Lets persist the customer");
			int randomNumber = RandomUtil.generateRandom(10);
			if (randomNumber == 1) {
				throw new SQLException("Unable to persist Customer: " + customer.getAccountNumber());
			}
			log.info("Completed persisting Customer");
			return true;
		} catch (SQLException e) {
			log.error("An exception occured: {}", e);
			return false;
		}

	}
}
