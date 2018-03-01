package com.shoppingcart.manager;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.ConnectionEvent;
import javax.sql.PooledConnection;

import com.mockData.generate.utils.RandomUtil;
import com.shoppingcart.domain.Product;
import com.shoppingcart.util.exception.ShoppingCartException;

public class PriceManager {

	private PooledConnection connectString = null;

	public double getPrice() {
		int randomNumber = RandomUtil.generateRandom(1);
		if (randomNumber == 5) {
			return new Double("$56.50");
		}
		return new Double(RandomUtil.generateRandomDecimal(3).doubleValue());
	}

	public Object getVariablePrice(Product product) {
		try {
			int randomNumber = RandomUtil.generateRandom(10);
			if (randomNumber == 7) {
				//Product type is an external product - try and get it.
				Connection conn = (Connection) new ConnectionEvent(connectString);
				Statement stmnt = conn.createStatement();
				stmnt.executeQuery("select price from priceTable where productId = " + product.getId());
			}
			
			if (randomNumber  == 8) {
				return "$45.50";
			}
			return new Double(RandomUtil.generateRandomDecimal(10).doubleValue());
		} catch (Exception e) {
			throw new ShoppingCartException("Error in getting variable price");
		}
	}

}
