package com.shoppingcart.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.utils.RandomUtil;
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

	private static final Logger log = LoggerFactory.getLogger(CartWorkflow.class);
	private CustomerManager custMgr = new CustomerManager();
	private ProductManager productMgr = new ProductManager();
	private SkuManager skewMgr = new SkuManager();
	private OrderManager orderMgr = new OrderManager();
	
	public CartWorkflow() {
		//Do nothing
	}
	
	/*
	 * Delete the Skew for Manufacturer
	 */
	public void cartWorkflow1() {
		log.info("Initiating Cart Workflow1 ...");			
		skewMgr.deleteSkuManufacturer();
	}

	/*
	 * Delete the Skew for Model
	 */
	public void cartWorkflow2() {
		log.info("Initiating Cart Workflow2 ...");			
		skewMgr.updateSkuModel();
	}
	
	public void cartWorkflow3() {
		log.info("Initiating Cart Workflow3 for a customer ...");
	
		log.info("First step, lets create a customer ...");
		Customer customer = custMgr.create("CUST" + RandomUtil.getAlphaNumericRandom(5));
		log.info("CartWorkflow3: The Customer Name is: {} {} ", customer.getFirstName(), customer.getLastName());
		customer.setActive(false);

		LoginManager loginManager = new LoginManager(customer);
		log.info("Now lets log the customer into into account");
		loginManager.login();
		log.info("Customer login successfull");
		
		log.info("Now, lets create a shopping cart for customer");
		ShoppingCartManager cart = new ShoppingCartManager(customer);
		log.info("Done - Completed creating shopping cart for customer");

		log.info("Now, Generating first product with Skew");
		Product firstProduct = productMgr.generateProductWithSku();
		Integer firstQuantity = RandomUtil.generateRandom(9);
		log.info("Lets add first product to the shopping cart");
		cart.add(firstProduct, firstQuantity);

		log.info("Now, Generating second product with Skew");
		Product secondProduct = productMgr.generateProductWithSku();
		Integer secondQuantity = RandomUtil.generateRandom(6);
		log.info("Lets add second product to the shopping cart");
		cart.add(secondProduct, secondQuantity);

		log.info("Now, Generating third product with Skew");
		Product thirdProduct = productMgr.generateProductWithSku();
		Integer thirdQuantity = RandomUtil.generateRandom(7);
		log.info("Lets add third product to the shopping cart");
		cart.add(thirdProduct, thirdQuantity);

		log.info("Last step - Lets validate the cart and checkout the cart ...");
		cart.validate();
		log.info("Validation complete ...");
		Order order = cart.checkout();
	
		log.info("One more - Generating invoice ...");
		orderMgr.generateInvoice(order);
		log.info("Cart Workflow3 complete - Invoice generation complete.");		
	}
}