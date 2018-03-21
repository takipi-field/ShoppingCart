package com.shoppingcart.manager;

import java.util.Map;

import com.mockData.generate.utils.DateUtils;
import com.mockData.generate.utils.RandomUtil;
import com.shoppingcart.domain.Customer;
import com.shoppingcart.domain.Order;
import com.shoppingcart.domain.OrderDetail;
import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.ShoppingCart;
import com.shoppingcart.util.exception.CustomerInActiveException;

public class ShoppingCartManager {

	private ShoppingCart cart = new ShoppingCart();
	
	public ShoppingCartManager(Customer customer) {
		cart.setCustomer(customer);
	}

	public void add(Product product, int quantity) {
		this.cart.add(product, quantity);
	}
	
	public Order checkout() {
		Map<Product, Integer> productQuantityMap = cart.getProductQuantityMap();
		
		Order order = new Order();
		order.setOrderNumber(RandomUtil.generateRandomAlphaString(3) + RandomUtil.generateRandomNumericString(6));
		order.setLastUpdated(DateUtils.getNow());
		order.setOrderDate(DateUtils.getNow());
		order.setCustomer(cart.getCustomer());

		productQuantityMap.forEach((product,quantity) -> process(order, product, quantity));
		order.setOrderComplete(true);
		return order;
	}

	private void process(Order order, Product product, Integer quantity) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderNumber(order.getOrderNumber());
		orderDetail.setOrderDetailNumber(RandomUtil.generateRandomAlphaString(3) + RandomUtil.generateRandomAlphaString(6));
		orderDetail.setProduct(product);
		orderDetail.setQuantity(quantity);
		
		order.add(orderDetail);
	}

	public void validate() {
		Customer customer = cart.getCustomer();
		if (!customer.isActive()) {
			throw new CustomerInActiveException("Cannot create an order for an inactive customer: Customer is: " + customer.getAccountNumber());
		}		
	}
}
