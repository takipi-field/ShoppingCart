package com.empire.shoppingcart.domain;

import java.util.Date;

public class OrderDetail extends BaseDomain {

	private String orderDetailNumber;
	private String orderNumber;
	private Product product;
	private Integer quantity;
	private Date shippedDate;
	private boolean cancelOrderDetail;

	public boolean isCancelOrderDetail() {
		return cancelOrderDetail;
	}
	public void setCancelOrderDetail(boolean cancelOrderDetail) {
		this.cancelOrderDetail = cancelOrderDetail;
	}
	public String getOrderDetailNumber() {
		return orderDetailNumber;
	}
	public void setOrderDetailNumber(String orderDetailNumber) {
		this.orderDetailNumber = orderDetailNumber;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Date getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
