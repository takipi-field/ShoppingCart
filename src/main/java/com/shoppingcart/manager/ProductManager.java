package com.shoppingcart.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.dao.ProductDAO;
import com.shoppingcart.dao.impl.ProductDAOImpl;
import com.shoppingcart.domain.Product;

public class ProductManager {

	private final static Logger log = LoggerFactory.getLogger(ProductManager.class);
	ProductDAO productDAO = new ProductDAOImpl();

	public Product get(String productNumber) {
		log.info("Getting the product with productNumber: " + productNumber);
		return productDAO.get(productNumber);
	}

	public Product generate() {
		log.info("Generating a product");
		return productDAO.generate();
	}

	public Product generateProductWithSku() {
		log.info("Generating a product with a Sku");
		return productDAO.generateProductWithSku();
	}

	public double getVariablePrice(Product product) {
		log.info("Getting a variable price for product");
		PriceManager priceMgr = new PriceManager();
		Object priceObj = priceMgr.getVariablePrice(product);
		Double price = (Double) priceObj;
		log.info("Got a variable price: " + price);
		return price.doubleValue();
	}

	public void update(Product product) {
		// TODO Auto generated
	}
}
