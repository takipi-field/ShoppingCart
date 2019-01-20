package com.shoppingcart.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockData.generate.DataGenerator;
import com.shoppingcart.dao.SkuDAO;
import com.shoppingcart.domain.SKU;
import com.shoppingcart.persist.PersistLayer;
import com.shoppingcart.util.exception.ShoppingCartException;

public class SkuDAOImpl implements SkuDAO {

	private DataGenerator dataGenerator = new DataGenerator();
	private final static Logger log = LoggerFactory.getLogger(SkuDAOImpl.class);
	private PersistLayer persistLayer = new PersistLayer();

	@Override
	public SKU get(String skuNumber) {
		log.info("Getting a Sku");
		SKU sku = dataGenerator.generateRandomSku(skuNumber);
		return sku;
	}

	public SKU generate() {
		log.info("Generating a Sku");
		SKU sku = dataGenerator.generateRandomSku();
		return sku;
	}

	@Override
	public SKU create(String skuNumber) {
		log.info("Creating a Sku");
		SKU sku = dataGenerator.generateRandomSku(skuNumber);
		return sku;
	}

	@Override
	public SKU update(SKU sku) {
		log.info("Updating Sku");
		boolean success = persistLayer.persistSku(sku);
		if (success)
			return sku;
		throw new ShoppingCartException("Unable to persist Sku " + sku.getSkuNumber());
	}

	@Override
	public boolean delete(SKU sku) {
		log.info("Deleting a Sku");
		persistLayer.deleteSku(sku);
		return true;
	}

}
