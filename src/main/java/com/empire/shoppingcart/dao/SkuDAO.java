package com.empire.shoppingcart.dao;

import com.empire.shoppingcart.domain.SKU;

public interface SkuDAO {

	public abstract SKU generate();
	public abstract SKU get(String skuNumber);
	public abstract SKU create(String skuNumber);
	public abstract SKU update(SKU sku);
	public abstract boolean delete(SKU sku);
}