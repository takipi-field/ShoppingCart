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
		CustomerManager custMgr = new CustomerManager();
		Customer customer = custMgr.get(customerNumber);
		Order order = customer.getLastOrder();
		return order;
	}

	public Order get(String orderNumber) {
		return orderDAO.get(orderNumber);
	}

	public Order get(String orderNumber, String customerNumber) {
		CustomerManager custMgr = new CustomerManager();
		Customer customer = custMgr.get(customerNumber);
		
		Order order = get(orderNumber);
		order.setCustomer(customer);
		return order;
	}

	public void updateOrderDate(Order order, String orderDate) {
		orderDAO.updateOrderDate(order, orderDate);
	}
	
	public List<Order> getAllOrders(String customerNumber) {
		return null;
	}
	
	public List<OrderDetail> getPendingOrderDetails(String orderNumber) {
		return null;
	}
	
	public boolean completeOrder(String orderNumber) {
		Order order = get(orderNumber);
		
		order.setOrderComplete(true);
		return true;
	}
	
	public boolean cancelOrder(String orderNumber) {
		Order order = get(orderNumber);
		
		if (order.isOrderComplete()) {
			log.error("Order is complete. Cannot cancel order");
			return false;
		} else if (order.isCancelOrder()) {
			log.error("Order is already cancelled.");
			return false;
		}
		order.setCancelOrder(true);
		return true;
	}
	
	public boolean cancelOrderDetail(String orderNumber, List<OrderDetail> orderDetails) {
		return false;
	}

	public void generateInvoice(Order order) {
		//TODO: Need to generate invoice
	}

	public void update(Order order) {
		orderDAO.update(order);
	}
}
