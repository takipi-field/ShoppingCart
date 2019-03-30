package com.shoppingcart.manager;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.ConnectionEvent;
import javax.sql.PooledConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.utils.RandomUtil;
import com.shoppingcart.domain.Product;
import com.shoppingcart.exception.ShoppingCartException;

public class PriceManager {

	private static final Logger log = LoggerFactory.getLogger(PriceManager.class);
	private PooledConnection connectString = null;

	public double getPrice() {
		log.info("Getting the price ...");
		int randomNumber = RandomUtil.generateRandom(1);
		if (randomNumber == 5) {
			return new Double("$56.50");
		}
		log.info("Returning the price ...");
		return RandomUtil.generateRandomDecimal(3).doubleValue();
	}

	public Object getVariablePrice(Product product) {
		try {
			log.info("Getting the variable price ...");
			int randomNumber = RandomUtil.generateRandom(10);
			if (randomNumber == 7) {
				//Product type is an external product - try and get it.
				Connection conn = (Connection) new ConnectionEvent(connectString);
				try (Statement stmnt = conn.createStatement()) {
					stmnt.executeQuery("select price from priceTable where productId = " + product.getId());
				}
			}
			log.info("Executed query to get the variable price ...");
			if (randomNumber  == 8) {
				return "$45.50";
			}
			return RandomUtil.generateRandomDecimal(10).doubleValue();
		} catch (Exception e) {
			throw new ShoppingCartException("Error in getting variable price");
		}
	}

}
