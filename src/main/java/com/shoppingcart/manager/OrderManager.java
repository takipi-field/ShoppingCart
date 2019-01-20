package com.shoppingcart.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.dao.OrderDAO;
import com.shoppingcart.dao.impl.OrderDAOImpl;
import com.shoppingcart.domain.Customer;
import com.shoppingcart.domain.Order;
import com.shoppingcart.domain.OrderDetail;

public class OrderManager {

	private final static Logger log = LoggerFactory.getLogger(OrderManager.class);
	OrderDAO orderDAO = new OrderDAOImpl();

	public Order getLatestOrder(String customerNumber) {
		log.info("Getting the latest Order for customer");
		CustomerManager custMgr = new CustomerManager();
		log.info("Lets get the customer");
		Customer customer = custMgr.get(customerNumber);
		log.info("Found the customer, lets get his last Order");
		Order order = customer.getLastOrder();
		log.info("Found latest Order - returning it");
		return order;
	}

	public Order get(String orderNumber) {
		log.info("Getting the Order");
		return orderDAO.get(orderNumber);
	}

	public Order get(String orderNumber, String customerNumber) {
		log.info("Getting the Order for a customer");
		CustomerManager custMgr = new CustomerManager();
		Customer customer = custMgr.get(customerNumber);
		
		log.info("Found the Customer, lets get the order");
		Order order = get(orderNumber);
		order.setCustomer(customer);
		return order;
	}

	public void updateOrderDate(Order order, String orderDate) {
		log.info("Updating the OrderDate");
		orderDAO.updateOrderDate(order, orderDate);
	}
	
	public List<Order> getAllOrders(String customerNumber) {
		log.info("Getting All the Ordes for a customer");
		return null;
	}
	
	public List<OrderDetail> getPendingOrderDetails(String orderNumber) {
		log.info("Getting all the pending orders for a customer");
		return null;
	}
	
	public boolean completeOrder(String orderNumber) {
		log.info("Lets complete the Order");
		Order order = get(orderNumber);
		log.info("Lets get the Order first");
		order.setOrderComplete(true);
		log.info("Lets complete the Order");
		return true;
	}
	
	public boolean cancelOrder(String orderNumber) {
		log.info("Cancelling the Order");
		Order order = get(orderNumber);
		
		log.info("We found the order");
		if (order.isOrderComplete()) {
			log.error("Order is complete. Cannot cancel order");
			return false;
		} else if (order.isCancelOrder()) {
			log.error("Order is already cancelled.");
			return false;
		}
		order.setCancelOrder(true);
		log.info("Completed cancelling Order");
		return true;
	}
	
	public boolean cancelOrderDetail(String orderNumber, List<OrderDetail> orderDetails) {
		return false;
	}

	public void generateInvoice(Order order) {
		//TODO: Need to generate invoice
	}

	public void update(Order order) {
		log.info("Updating the Order");
		orderDAO.update(order);
	}
}
