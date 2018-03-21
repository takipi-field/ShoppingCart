package com.mockData.generate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mockData.generate.utils.DataTypeMappingPropertyReader;
import com.mockData.generate.utils.DateUtils;
import com.mockData.generate.utils.RandomUtil;
import com.shoppingcart.domain.Address;
import com.shoppingcart.domain.Customer;
import com.shoppingcart.domain.CustomerType;
import com.shoppingcart.domain.Email;
import com.shoppingcart.domain.Order;
import com.shoppingcart.domain.OrderDetail;
import com.shoppingcart.domain.Phone;
import com.shoppingcart.domain.Product;
import com.shoppingcart.domain.SKU;
import com.shoppingcart.manager.PriceManager;

public class DataGenerator {

	private static Map<String, List<String>> dataTypePropertiesMap = 
		DataTypeMappingPropertyReader.getDataTypePropertiesMap();
	private PriceManager priceManager = new PriceManager();


	public Customer createCustomer(String customerNumber) throws ParseException {
		Customer customer = generateCustomer(customerNumber);
		
		String orderNumber = RandomUtil.generateRandomAlphaString(3) + RandomUtil.generateRandomNumbers(4);
		Order lastOrder = generateOrder1(orderNumber);
		
		List<OrderDetail> orderDetailList = getOrderDetails1(orderNumber);
		lastOrder.setOrderDetailsList(orderDetailList);
		
		lastOrder.setCustomer(customer);
		customer.setLastOrder(lastOrder);
		
		return customer;
	}

	
	public Customer createCustomer1() throws ParseException {
		String customerNumber = RandomUtil.generateRandomAlphaString(3) + RandomUtil.generateRandomNumbers(4);
		return createCustomer(customerNumber);
	}


	private List<OrderDetail> getOrderDetails1(String orderNumber) {
		Product product1 = generateProduct();
		SKU sku1 = generateSkuColor();
		SKU sku2 = generateSkuSize();
		List<SKU> skuList1 = new ArrayList<SKU>();
		skuList1.add(sku1);skuList1.add(sku2);
		product1.setSkus(skuList1);

		Product product2 = generateProduct();
		SKU sku3 = generateSkuColor();
		SKU sku4 = generateSkuSize();
		List<SKU> skuList2 = new ArrayList<SKU>();
		skuList2.add(sku3);skuList2.add(sku4);
		product2.setSkus(skuList2);
		
		OrderDetail orderDetail1 = generateOrderDetail(orderNumber);
		orderDetail1.setProduct(product1);
		
		OrderDetail orderDetail2 = generateOrderDetail(orderNumber);
		orderDetail2.setProduct(product2);

		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList.add(orderDetail1);orderDetailList.add(orderDetail2);
		return orderDetailList;
	}
	
	
	public Customer generateCustomer(String customerNumber) throws ParseException {
		Customer customer = new Customer();
		
		customer.setAccountNumber(customerNumber);
		customer.setId(RandomUtil.generateRandom(999999));
		customer.setFirstName(DataTypeGenerator.getRandomDataType("FIRST_NAMES"));
		customer.setLastName(DataTypeGenerator.getRandomDataType("LAST_NAMES"));
		customer.setActive(true);
		customer.setCreatedDateTime(DateUtils.getNow());
		customer.setCustomerType(generateCustomerType());
		customer.setSsn(RandomUtil.generateRandomSSN());

		Address homeAddress = generateAddress("home", true);
		Address workAddress = generateAddress("work", false);
		
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(homeAddress); addressList.add(workAddress);
		customer.setAddresses(addressList);

		Email email1 = generateEmail(customer.getFullName(), "work", true);
		Email email2 = generateEmail(customer.getFullName(), "home", false);
		List<Email> emailList = new ArrayList<Email>();
		emailList.add(email1); emailList.add(email2);
		customer.setEmails(emailList);
		
		Phone phone1 = generatePhone("mobile", true);
		Phone phone2 = generatePhone("work", false);
		List<Phone> phoneList = new ArrayList<Phone>();
		phoneList.add(phone1); phoneList.add(phone2);
		customer.setPhoneNumbers(phoneList);

		String dateString = DateUtils.getRandomDateString();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		customer.setDob(format.parse(dateString));
		
		CustomerNotes notes = new CustomerNotes();
		customer.setNotes(notes.getCustomerNotes());
		customer.setLastUpdated(DateUtils.getNow());
		return customer;
	}

	private Phone generatePhone(String string, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}


	private Email generateEmail(String fullName, 
			String emailAddressType, boolean defaultInd) {
		Email email = new Email();
		email.setId(RandomUtil.generateRandom(999999));
		email.setDefaultInd(defaultInd);
		email.setLastUpdated(DateUtils.getNow());
		email.setEmailAddress(fullName + DataTypeGenerator.getRandomDataType("EMAIL_PROVIDERS"));
		email.setEmailType(emailAddressType);
		
		return email;
	}


	private Address generateAddress(String addressType, boolean defaultInd) {
		Address address = new Address();
		address.setLine1(DataTypeGenerator.getRandomDataType("STREET_ADDRESS"));
		address.setLine2(null);
		address.setCity(DataTypeGenerator.getRandomDataType("US_CITIES"));
		address.setState(DataTypeGenerator.getRandomDataType("US_STATES_ABBR"));
		address.setZipcode(RandomUtil.getRandomNumberInRange(10001, 99999) + "");
		address.setId(RandomUtil.generateRandom(999999));
		address.setAddressType(addressType);
		address.setLastUpdated(DateUtils.getNow());
		address.setDefaultInd(defaultInd);
		
		return address;
	}


	public Order generateOrder1(String orderNumber) {
		Order order = new Order();
			
		order.setOrderNumber(orderNumber);
		order.setId(RandomUtil.generateRandom(999999));
		order.setOrderDate(DateUtils.getNow());
		order.setCancelOrder(false);
		order.setOrderDate(DateUtils.getNow());
		order.setOrderComplete(false);
		order.setLastUpdated(DateUtils.getNow());
		return order;
	}
	
	public Order generateOrder2(String orderNumber) {
		Order order = generateOrder1(orderNumber);
		List<OrderDetail> orderDetailList = getOrderDetails1(orderNumber);
		order.setOrderDetailsList(orderDetailList);
		return order;
	}
	
	public OrderDetail generateOrderDetail(String orderNumber) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderNumber(orderNumber);
		orderDetail.setOrderDetailNumber(RandomUtil.generateRandomNumeric(5).toString());
		orderDetail.setShippedDate(DateUtils.getNow());
		
		orderDetail.setCancelOrderDetail(false);
		orderDetail.setLastUpdated(DateUtils.getNow());
		return orderDetail;
	}
	
	private CustomerType generateCustomerType() {
		CustomerType customerType = new CustomerType();

		customerType.setName(DataTypeGenerator.getRandomDataType("CUSTOMER_TYPE"));
		customerType.setDescription(DataTypeGenerator.getRandomDataType("CUSTOMER_TYPE"));
		customerType.setLastUpdated(DateUtils.getNow());
		return customerType;
	}

	public Product generateProduct() {
		return generateProduct(RandomUtil.generateRandomNumeric(5).toString());
	}
	
	public Product generateProduct(String productNumber) {
		List<String> valueList = dataTypePropertiesMap.get("PRODUCT_NAMES");
		int randomNumber = RandomUtil.getRandomNumberInRange(0, valueList.size());

		
		Product product = new Product();
		product.setProductNumber(productNumber);
		product.setAvailableQuantity(RandomUtil.generateRandomNumeric(1));
		
		product.setProductName(DataTypeGenerator.getRandomDataType("PRODUCT_NAMES", randomNumber));
		product.setProductCategory(DataTypeGenerator.getRandomDataType("PRODUCT_CATEGORY", randomNumber));
		product.setProductDescription(DataTypeGenerator.getRandomDataType("PRODUCT_DESCRIPTION", randomNumber));
		product.setDiscontinued(new Boolean(DataTypeGenerator.getRandomDataType("TRUE_FALSE_WEIGHTED").toLowerCase()));
		product.setEstShipDate(DateUtils.getNow());
		product.setFixedPrice(true);
		
		product.setPrice(priceManager.getPrice());
		product.setFixedPrice(true);
		product.setLastUpdated(DateUtils.getNow());
		return product;
	}
	
	public Product generateVariablePriceProduct() {
		Product product = generateProduct();
		product.setFixedPrice(false);
		
		double price = (double) priceManager.getVariablePrice(product);
		product.setPrice(price);
		
		return product;
	}
	
	public SKU generateSkuColor() {
		String skuNumber = RandomUtil.generateRandomAlphaString(5);
		return generateSkuColor(skuNumber);
	}

	public SKU generateSkuColor(String skuNumber) {
		SKU sku = new SKU();
		
		sku.setSkuNumber(skuNumber);
		sku.setSkuType("COLOR");
		sku.setSkuDescription("Color of Product");
		sku.setSkuName("Color");
		sku.setValue(DataTypeGenerator.getRandomDataType("COLOR"));	
		sku.setLastUpdated(DateUtils.getNow());
		return sku;
	}
	
	public SKU generateSkuSize(String skuNumber) {
		SKU sku = new SKU();
		
		sku.setSkuNumber(skuNumber);
		sku.setSkuType("SIZE");
		sku.setSkuDescription("Size of product");
		sku.setSkuName("Size");
		sku.setValue(DataTypeGenerator.getRandomDataType("SIZE"));
		sku.setLastUpdated(DateUtils.getNow());
		return sku;
	}

	public SKU generateSkuSize() {
		String skuNumber = RandomUtil.generateRandomAlphaString(5);
		return generateSkuSize(skuNumber);
	}

	public SKU generateRandomSku() {
		String skuNumber = RandomUtil.generateRandomAlphaString(5);
		return generateRandomSku(skuNumber);
	}

	public SKU generateRandomSku(String skuNumber) {
		int x = RandomUtil.generateRandom(2);
		if (x % 2 == 0) {
			return generateSkuSize(skuNumber);
		} else {
			return generateSkuColor(skuNumber);
		}
	}
}