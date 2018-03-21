package com.shoppingcart.workflow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockData.generate.utils.DateUtils;
import com.mockData.generate.utils.RandomUtil;
import com.shoppingcart.domain.Customer;
import com.shoppingcart.domain.Order;
import com.shoppingcart.domain.OrderDetail;
import com.shoppingcart.domain.Product;
import com.shoppingcart.manager.CustomerManager;
import com.shoppingcart.manager.LoginManager;
import com.shoppingcart.manager.OrderManager;
import com.shoppingcart.manager.ProductManager;
import com.shoppingcart.manager.ShoppingCartManager;
import com.shoppingcart.manager.SkuManager;
import com.shoppingcart.util.FileReader;

public class ShoppingCartWorkflow {

	private final static Logger log = LoggerFactory.getLogger(ShoppingCartWorkflow.class);
	private 	CustomerManager custMgr = new CustomerManager();
	private ProductManager productMgr = new ProductManager();
	private SkuManager skewMgr = new SkuManager();
	private OrderManager orderMgr = new OrderManager();
	
	public ShoppingCartWorkflow() {
	}

	/*
	 * Workflow1 - Create customer, login, create a shopping cart, add 2 products (with Skew), checkout and generate Invoice
	 */
	public void workflow1() {
		log.info("Initiating ShoppingCart Workflow1 for customer ...");
	
		log.info("Creating a customer ...");
		Customer customer = custMgr.create();		
		log.info("Customer Name is: " + customer.getFirstName() + " " + customer.getLastName());

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
		
		log.info("Lets checkout the cart ...");
		Order order = cart.checkout();
	
		log.info("Generating invoice ...");
		orderMgr.generateInvoice(order);
		log.info("Invoice generation complete.");		
	}

	/*
	 * Workflow2 - Creating a customer, setting him to inactive and persisting the customer.
	 */
	public void workflow2() {
		log.info("Initiating ShoppingCart Workflow2 ...");

		log.info("Creating a customer ...");
		Customer customer = custMgr.create();		
		log.info("Customer Name is: " + customer.getFirstName() + " " + customer.getLastName());
		
		//Setting customer to inactive
		log.info("Setting customer to inactive.");
		customer.setActive(false);
		log.info("Updating customer ...");
		custMgr.update(customer);
		log.info("Customer successfully updated.");
	}

	/*
	 * Workflow3 - Find an existing customer and delete him/her.
	 */
	public void workflow3() {
		log.info("Initiating ShoppingCart Workflow3 ...");

		log.info("Finding an existing customer ...");
		Customer customer = custMgr.get("CUST1003");	
		log.info("Customer Name is: " + customer.getFirstName() + " " + customer.getLastName());
		
		log.info("Deleting Customer: " + customer.getAccountNumber());
		custMgr.delete(customer);
		log.info("Customer successfully deleted.");
	}

	/*
	 * Workflow4 - Find an existing order and update the order date
	 */
	public void workflow4() {
		log.info("Initiating ShoppingCart Workflow4 ...");
		
		log.info("Finding an existing order ...");
		Order order = orderMgr.get("ORD352035");	
		log.info("Successfully received order: " + order.getOrderNumber());
		
		log.info("Updating order date");
		orderMgr.updateOrderDate(order, DateUtils.getRandomDateString(7));
		log.info("Order date updated successfully.");
	}

	/*
	 * Workflow5 - Get the customer for an existing order
	 */
	public void workflow5() {
		log.info("Initiating ShoppingCart Workflow5 ...");
		
		log.info("Get the customer for an existing order ...");
		Order order = orderMgr.get("ORD352035");	
		log.info("Successfully received order: " + order.getOrderNumber());

		Customer customer = order.getCustomer();
		log.info("Customer Name is: " + customer.getFirstName() + " " + customer.getLastName());
	}		

	/*
	 * Workflow6 - Get the order details for an existing order
	*/
	public void workflow6() {
		log.info("Initiating ShoppingCart Workflow6 ...");
		
		log.info("Find Status of existing order ...");
		Order order = orderMgr.get("ORD335035");	
		log.info("Successfully received order: " + order.getOrderNumber());

		List<OrderDetail> orderDetails = order.getOrderDetailsList();
		for (OrderDetail orderDetail : orderDetails) {
			log.info("Product is: " + orderDetail.getProduct().getProductName());			
			log.info("OrderDetail shipped date is: " + orderDetail.getShippedDate());
		}		
	}

	/*
	 * Get the order status from a file, and update it.
	 */
	public void workflow7() {
		log.info("Initiating ShoppingCart Workflow7 ...");

		log.info("Get the existing order ...");
		Order order = orderMgr.get("ORD335035");
		
		log.info("Get the Order status from an external file.");
		List<String> contents = FileReader.get("OrderStatus.txt");
		Boolean orderComplete = new Boolean(contents.get(1));
		order.setOrderComplete(orderComplete);
		log.info("Completed updating order staus.");
	}

	/*
	 * UpdateOrderShipped date for existing order;
	 */
	public void workflow8() throws ParseException {
		log.info("Initiating ShoppingCart Workflow8 ...");
		
		log.info("Find Status of existing order ...");
		Order order = orderMgr.get("ORD224525");	
		log.info("Successfully received order: " + order.getOrderNumber());

		List<OrderDetail> orderDetails = order.getOrderDetailsList();
		for (OrderDetail orderDetail : orderDetails) {
			log.info("Product is: " + orderDetail.getProduct().getProductName());
			String dateStr = DateUtils.getRandomDateString(7);
			
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			orderDetail.setShippedDate(format.parse(dateStr));
			log.info("Updated shipped date for orderDetail: " + orderDetail.getOrderDetailNumber());
		}
		orderMgr.update(order);
	}
	
	/*
	 * Create new product, skew combination
	 */
	public void workflow9() {
		log.info("Initiating ShoppingCart Workflow9 ...");

		log.info("Generating product with Skew");
		Product product = productMgr.generateProductWithSkew();
		log.info("Completerd generating product with Skew");
		
		log.info("Lets get the variable price");
		double price = productMgr.getVariablePrice(product);
		product.setFixedPrice(false);
		product.setPrice(price);
		log.info("Completed assigning variable price to product.");
		
		log.info("Updating product");
		productMgr.update(product);
		log.info("Completed updating product");
	}

	/*
	 * Create new skew. If the SkewType is COLOR, change it to COLOR_INDICATOR. Update it
	 */
	public void workflow10() {
		log.info("Initiating ShoppingCart Workflow10 ...");
		skewMgr.generateSkuAndChangeSkuTypeColor();
	}

	public void workflow11() {
		log.info("Initiating ShoppingCart Workflow11 ...");
		String customerNumber = "CUST1004";
		Customer customer = custMgr.find(customerNumber);
		if (customer == null) {
			log.info("Could not find the customer with customerNumber: " + customerNumber);
			//Could not find a customer, lets create an Overops Event to remind us.
			custMgr.create(customerNumber);
		}
	}
}