package com.shoppingcart.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockData.generate.utils.RandomUtil;
import com.shoppingcart.domain.Customer;
import com.shoppingcart.domain.Order;
import com.shoppingcart.domain.Product;
import com.shoppingcart.manager.CustomerManager;
import com.shoppingcart.manager.LoginManager;
import com.shoppingcart.manager.OrderManager;
import com.shoppingcart.manager.ProductManager;
import com.shoppingcart.manager.ShoppingCartManager;
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
	
	public void workflow3() {
		log.info("Initiating ShoppingCart Workflow3 for customer ...");
	
		log.info("Creating a customer ...");
		Customer customer = custMgr.create();		
		log.info("Customer Name is: " + customer.getFirstName() + " " + customer.getLastName());
		//Invalidating the customer for this test.
		customer.setActive(false);

		log.info("Customer logs into account");
		LoginManager loginManager = new LoginManager(customer);
		loginManager.login();
		log.info("Customer login successfull");
		
		log.info("Creating a shopping cart for customer: " + customer.getAccountNumber());
		ShoppingCartManager cart = new ShoppingCartManager(customer);
		log.info("Completed creating shopping cart for customer");

		log.info("Generating first product with Skew");
		Product product1 = productMgr.generateProductWithSkew();
		Integer quantity1 = RandomUtil.generateRandom(8);
		log.info("Adding first product to the shopping cart");
		cart.add(product1, quantity1);

		log.info("Generating second product with Skew");
		Product product2 = productMgr.generateProductWithSkew();
		Integer quantity2 = RandomUtil.generateRandom(10);
		log.info("Adding second product to the shopping cart");
		cart.add(product2, quantity2);
		
		log.info("Lets validate the cart and checkout the cart ...");
		cart.validate();
		Order order = cart.checkout();
	
		log.info("Generating invoice ...");
		orderMgr.generateInvoice(order);
		log.info("Invoice generation complete.");		
	}
}