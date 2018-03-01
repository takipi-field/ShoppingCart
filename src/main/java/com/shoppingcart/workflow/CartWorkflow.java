package com.shoppingcart.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.manager.CustomerManager;
import com.shoppingcart.manager.OrderManager;
import com.shoppingcart.manager.ProductManager;
import com.shoppingcart.manager.SkuManager;

public class CartWorkflow {

	private final static Logger log = LoggerFactory.getLogger(CartWorkflow.class);
	private 	CustomerManager custMgr = new CustomerManager();
	private ProductManager productMgr = new ProductManager();
	private SkuManager skewMgr = new SkuManager();
	private OrderManager orderMgr = new OrderManager();
	
	public CartWorkflow() {
	}
	
	/*
	 * Delete the Skew for Manufacturer
	 */
	public void workflow1() {
		log.info("Initiating Cart Workflow1 ...");			
		skewMgr.deleteSkuManufacturer();
	}
	
	/*
	 * Delete the Skew for Model
	 */
	public void workflow2() {
		log.info("Initiating Cart Workflow2 ...");			
		skewMgr.updateSkuModel();
	}
}