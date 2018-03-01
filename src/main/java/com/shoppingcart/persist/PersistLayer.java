package com.shoppingcart.persist;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockData.generate.utils.RandomUtil;
import com.shoppingcart.domain.Customer;
import com.shoppingcart.domain.Order;
import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.SKU;
import com.shoppingcart.util.ExceptionUtils;
import com.shoppingcart.util.exception.SQLException;
import com.shoppingcart.util.exception.SkuException;

public class PersistLayer {

	private final static Logger log = LoggerFactory.getLogger(PersistLayer.class);

	public boolean persist(Order order) {
		try {
			int randomNumber = RandomUtil.generateRandom(10);
			if (randomNumber == 5) {
				throw new SQLException("Unable to persist Order: " + order.getOrderNumber());
			}
			return true;
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(ExceptionUtils.convertStackTraceToString(e));
			return false;
		}
	}
	
	public void updateOrderDate(Order order, String dateStr) throws ParseException {
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = format.parse(dateStr);
		order.setLastUpdated(date);
	}
	
	public boolean updateOrder(Order order) {
		Integer id = new Integer(order.getId());
		if (id.compareTo(new Integer(0)) > 0) {
			return false;
		}
		return true;
	}

	public boolean persist(Product product) throws SQLException {
		int randomNumber = RandomUtil.generateRandom(10);
		if (randomNumber == 5) {
			throw new SQLException("Unable to persist product: " + product.getProductNumber());
		}
		return true;
	}

	public boolean persistSku(SKU sku) {
		try {
			int randomNumber = RandomUtil.generateRandom(10);
			if (randomNumber == 8) {
				throw new SQLException("Unable to persist Sku: " + sku.getSkuNumber());
			}
			return true;
		} catch (SQLException e) {
			//Swallow exception ...
			return false;
		}
	}
	
	public boolean deleteSku(SKU sku) {
		int randomNumber = RandomUtil.generateRandom(101);
		if (randomNumber == 99) {
			throw new SkuException("Unable to delete Sku: " + sku.getSkuNumber());
		}
		return true;
	}

	public boolean persist(Customer customer) {
		try {
			int randomNumber = RandomUtil.generateRandom(10);
			if (randomNumber == 1) {
				throw new SQLException("Unable to persist Customer: " + customer.getAccountNumber());
			}
			return true;
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(ExceptionUtils.convertStackTraceToString(e));
			return false;
		}

	}
}
