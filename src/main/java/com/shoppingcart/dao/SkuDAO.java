package com.shoppingcart.dao;

import com.shoppingcart.domain.SKU;

public interface SkuDAO {

	public abstract SKU generate();
	public abstract SKU get(String skewNumber);
	public abstract SKU create(String skewNumber);
	public abstract SKU update(SKU skew);
	public abstract boolean delete(SKU skew);
}