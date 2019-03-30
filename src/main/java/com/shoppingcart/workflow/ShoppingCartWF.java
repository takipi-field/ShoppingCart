package com.shoppingcart.workflow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.utils.DateUtils;
import com.mockdata.generate.utils.RandomUtil;
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

public class ShoppingCartWF {

	private static final Logger log = LoggerFactory.getLogger(ShoppingCartWF.class);
	private CustomerManager custMgr = new CustomerManager();
	private ProductManager productMgr = new ProductManager();
	private SkuManager skewMgr = new SkuManager();
	private OrderManager orderMgr = new OrderManager();
	
	public ShoppingCartWF() {
		//Do nothing
	}

	/*
	 * Workflow1 - Create customer, login, create a shopping cart, add 2 products (with Skew), checkout and generate Invoice
	 */
	public void workflow1() {
		log.info("Initiating ShoppingCart Workflow1 for customer ...");
	
		log.info("Creating a customer ...");
		Customer customer = custMgr.create();		
		log.info("WorkFlow1: Customer Name is: {} {} ", customer.getFirstName() , customer.getLastName());

		log.info("Customer logs into account");
		LoginManager loginManager = new LoginManager(customer);
		loginManager.login();
		log.info("Customer login successfull");
		
		log.info("Creating a shopping cart for customer: {}", customer.getAccountNumber());
		ShoppingCartManager cart = new ShoppingCartManager(customer);
		log.info("Completed creating shopping cart for customer");

		log.info("Generating first product with Skew");
		Product product1 = productMgr.generateProductWithSku();
		Integer quantity1 = RandomUtil.generateRandom(8);
		log.info("Adding first product to the shopping cart");
		cart.add(product1, quantity1);

		log.info("Generating second product with Skew");
		Product product2 = productMgr.generateProductWithSku();
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
		log.info("Workflow2: Customer Name is: {} {} ", customer.getFirstName() , customer.getLastName());
		log.info("Workflow2: Customer Number is: {} ", customer.getAccountNumber());
		
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
		RandomUtil.setSeed();
		String customerNumber = "CUST" + RandomUtil.generateRandomNumbers(4);
		Customer customer = custMgr.get(customerNumber);	
		log.info("Workflow3: Customer Name is: {} {} ", customer.getFirstName() , customer.getLastName());
		
		log.info("Deleting Customer: {}", customer.getAccountNumber());
		custMgr.delete(customer);
		log.info("Customer successfully deleted.");
	}

	/*
	 * Workflow4 - Find an existing order and update the order date
	 */
	public void workflow4() {
		log.info("Initiating ShoppingCart Workflow4 ...");
		
		log.info("Finding an existing order ...");
		RandomUtil.setSeed();
		String customerNumber = RandomUtil.generateRandomAlphaString(3) + RandomUtil.generateRandomNumbers(4);
		Customer customer = custMgr.get(customerNumber);	

		String orderNumber = "ORD" + RandomUtil.generateRandomNumbers(4);
		Order order = orderMgr.get(orderNumber, customer.getAccountNumber());	
		log.info("workflow4: Sucessfully received order: {}", order.getOrderNumber());
		
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
		RandomUtil.setSeed();
		String orderNumber = "ORD" + RandomUtil.generateRandomNumbers(4);

		Order order = orderMgr.get(orderNumber);	
		log.info("workflow5: Received order: {}", order.getOrderNumber());

		Customer customer = order.getCustomer();
		log.info("Workflow5: Customer Name is: {} {} ", customer.getFirstName() , customer.getLastName());
	}		

	/*
	 * Workflow6 - Get the order details for an existing order
	*/
	public void workflow6() {
		log.info("Initiating ShoppingCart Workflow6 ...");
		
		log.info("Find Status of existing order ...");

		RandomUtil.setSeed();
		String orderNumber = "ORD" + RandomUtil.generateRandomNumbers(4);

		Order order = orderMgr.get(orderNumber);	
		log.info("workflow6: Sucessfully got order number : {}", order.getOrderNumber());

		List<OrderDetail> orderDetails = order.getOrderDetailsList();
		for (OrderDetail orderDetail : orderDetails) {
			log.info("Product is: {}", orderDetail.getProduct().getProductName());			
			log.info("OrderDetail shipped date is: {}", orderDetail.getShippedDate());
		}		
	}

	/*
	 * Get the order status from a file, and update it.
	 */
	public void workflow7() {
		log.info("Initiating ShoppingCart Workflow7 ...");

		log.info("Get the existing order ...");
		
		RandomUtil.setSeed();
		String orderNumber = "ORD" + RandomUtil.generateRandomNumbers(4);
		Order order = orderMgr.get(orderNumber);
		
		log.info("Get the Order status from an external file.");
		List<String> contents = FileReader.get("OrderStatus.txt");
		Boolean orderComplete = new Boolean(contents.get(1));
		order.setOrderComplete(orderComplete);
		log.info("Completed updating order staus.");
	}

	/*
	 * Workflow8 - UpdateOrderShipped date for existing order
	 */
	public void workflow8() throws ParseException {
		log.info("Initiating ShoppingCart Workflow8 ...");
		
		log.info("Find Status of existing order ...");
		
		RandomUtil.setSeed();
		String orderNumber = "ORD" + RandomUtil.generateRandomNumbers(5);
		Order order = orderMgr.get(orderNumber);
		log.info("workflow8: Sucessfully found order no: {}", order.getOrderNumber());

		List<OrderDetail> orderDetails = order.getOrderDetailsList();
		for (OrderDetail orderDetail : orderDetails) {
			log.info("Product is: {}", orderDetail.getProduct().getProductName());
			String dateStr = DateUtils.getRandomDateString(7);
			
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			orderDetail.setShippedDate(format.parse(dateStr));
			log.info("Updated shipped date for orderDetail: {}", orderDetail.getOrderDetailNumber());
		}
		orderMgr.update(order);
	}

	/*
	 * Workflow9 - Create new product, skew combination
	 */
	public void workflow9() {
		log.info("Initiating ShoppingCart Workflow9 ...");

		log.info("Generating product with Skew");
		Product product = productMgr.generateProductWithSku();
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
	 * Workflow 10 - Create new skew. If the SkewType is COLOR, change it to COLOR_INDICATOR. Update it
	 */
	public void workflow10() {
		log.info("Initiating ShoppingCart Workflow10 ...");
		skewMgr.generateSkuAndChangeSkuTypeColor();
	}

	public void workflow11() {
		log.info("Initiating ShoppingCart Workflow11 ...");
		String customerNumber = "CUST" + RandomUtil.generateRandomNumbers(4);
		Customer customer = custMgr.find(customerNumber);
		if (customer == null) {
			log.info("Could not find the customer with customerNumber: {}", customerNumber);
			//Could not find a customer, lets create an Overops Event to remind us.
			custMgr.create(customerNumber);
		}
	}
}