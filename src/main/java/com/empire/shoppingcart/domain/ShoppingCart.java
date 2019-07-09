package com.empire.shoppingcart.domain;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart extends BaseDomain {

	private Customer customer;
	private Map<Product, Integer> productQuantityMap = new HashMap<>();

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Map<Product, Integer> getProductQuantityMap() {
		return productQuantityMap;
	}
	public void setProductQuantityMap(Map<Product, Integer> productQuantityMap) {
		this.productQuantityMap = productQuantityMap;
	}
	public void add(Product product, Integer newQuantity) {
		if (this.productQuantityMap.containsKey(product)) {
			//NeedToDo: put in some business logic and throw some exception here.
			this.productQuantityMap.put(product, newQuantity);
		} else {
			this.productQuantityMap.putIfAbsent(product, newQuantity);
		}
	}
}
