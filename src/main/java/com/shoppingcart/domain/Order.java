package com.shoppingcart.domain;

import java.util.Date;
import java.util.List;

public class Order extends BaseDomain {

	private String orderNumber;
	private List<OrderDetail> orderDetailsList = null;
	private Date orderDate;
	private boolean orderComplete;
	private boolean cancelOrder;
	private Customer customer;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public List<OrderDetail> getOrderDetailsList() {
		return orderDetailsList;
	}
	public void setOrderDetailsList(List<OrderDetail> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public boolean isOrderComplete() {
		return orderComplete;
	}
	public void setOrderComplete(boolean orderComplete) {
		this.orderComplete = orderComplete;
	}
	public boolean isCancelOrder() {
		return cancelOrder;
	}
	public void setCancelOrder(boolean cancelOrder) {
		this.cancelOrder = cancelOrder;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void add(OrderDetail orderDetail) {
		orderDetailsList.add(orderDetail);
	}
}
