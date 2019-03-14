package com.shoppingcart.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockData.generate.DataGenerator;
import com.shoppingcart.dao.ProductDAO;
import com.shoppingcart.domain.Product;
import com.shoppingcart.persist.PersistLayer;
import com.shoppingcart.util.ExceptionUtils;
import com.shoppingcart.util.exception.SQLException;
import com.shoppingcart.exception.ShoppingCartException;

public class ProductDAOImpl implements ProductDAO {

	private DataGenerator dataGenerator = new DataGenerator();
	private final static Logger log = LoggerFactory.getLogger(ProductDAOImpl.class);
	private PersistLayer persistLayer = new PersistLayer();

	@Override
	public Product get(String productNumber) {
		log.info("Getting a product");
		Product product = dataGenerator.generateProduct(productNumber);
		return product;
	}

	public Product generate() {
		log.info("Generating a product");
		Product product = dataGenerator.generateProduct();
		return product;
	}
	
	public Product generateProductWithSku() {
		log.info("Generating a product with Sku");

		Product product = dataGenerator.generateProduct();
		product.addSku(dataGenerator.generateRandomSku());
		return product;
	}

	@Override
	public Product create(String productNumber) {
		log.info("Creating a product");

		Product product = dataGenerator.generateProduct(productNumber);
		return product;
	}

	@Override
	public Product update(Product product) {
		try {
			log.info("Updating Product");
			boolean success = persistLayer.persist(product);
			if (success)
				return product;
		} catch (SQLException e) {
			log.error(ExceptionUtils.convertStackTraceToString(e));
		}
		return null;
	}

	@Override
	public boolean delete(Product product) {
		try {
			log.info("Deleting Product");
			boolean success = persistLayer.persist(product);
			if (success)
				return true;
		} catch (Exception e) {
			throw new ShoppingCartException("Unable to delete Product " + product.getProductNumber());			
		}
		return false;
	}
}
