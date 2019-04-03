package com.mockdata.generate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.utils.DataTypeMappingPropertyReader;
import com.mockdata.generate.utils.DateUtils;
import com.mockdata.generate.utils.RandomUtil;
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

	private static final Logger log = LoggerFactory.getLogger(DataGenerator.class);
	private static Map<String, List<String>> dataTypePropertiesMap = 
		DataTypeMappingPropertyReader.getDataTypePropertiesMap();
	private PriceManager priceManager = new PriceManager();


	public Customer createCustomer(String customerNumber) throws ParseException {
		log.info("Creating a customer");
		Customer customer = generateCustomer(customerNumber);
		
		String orderNumber = RandomUtil.generateRandomAlphaString(3) + RandomUtil.generateRandomNumbers(4);
		Order lastOrder = generateOrder1(orderNumber);
		
		List<OrderDetail> orderDetailList = getOrderDetails1(orderNumber);
		lastOrder.setOrderDetailsList(orderDetailList);
		
		lastOrder.setCustomer(customer);
		customer.setLastOrder(lastOrder);
		
		log.info("Completed creating Customer");
		return customer;
	}

	
	public Customer createCustomer1() throws ParseException {
		log.info("Creating a customer");
		RandomUtil.setSeed();
		String customerNumber = RandomUtil.generateRandomAlphaString(3) + RandomUtil.generateRandomNumbers(4);
		return createCustomer(customerNumber);
	}


	private List<OrderDetail> getOrderDetails1(String orderNumber) {
		log.info("Creating order details");
		Product product1 = generateProduct();
		SKU sku1 = generateSkuColor();
		SKU sku2 = generateSkuSize();
		List<SKU> skuList1 = new ArrayList<>();
		skuList1.add(sku1);skuList1.add(sku2);
		product1.setSkus(skuList1);

		log.info("Generating a product with Skew");
		Product product2 = generateProduct();
		SKU sku3 = generateSkuColor();
		SKU sku4 = generateSkuSize();
		List<SKU> skuList2 = new ArrayList<>();
		skuList2.add(sku3);skuList2.add(sku4);
		product2.setSkus(skuList2);
		
		log.info("Generating Order Details");
		OrderDetail orderDetail1 = generateOrderDetail(orderNumber);
		orderDetail1.setProduct(product1);
		
		log.info("Creating a second Order");
		OrderDetail orderDetail2 = generateOrderDetail(orderNumber);
		orderDetail2.setProduct(product2);

		List<OrderDetail> orderDetailList = new ArrayList<>();
		orderDetailList.add(orderDetail1);orderDetailList.add(orderDetail2);
		
		log.info("Lets return the order details");
		return orderDetailList;
	}
	
	
	public Customer generateCustomer(String customerNumber) throws ParseException {
		log.info("Generating a new customer");
		Customer customer = new Customer();
		
		customer.setAccountNumber(customerNumber);
		customer.setId(RandomUtil.generateRandom(999999));
		customer.setFirstName(DataTypeGenerator.getRandomDataType("FIRST_NAMES"));
		customer.setLastName(DataTypeGenerator.getRandomDataType("LAST_NAMES"));
		customer.setActive(true);
		customer.setCreatedDateTime(DateUtils.getNow());
		customer.setCustomerType(generateCustomerType());
		customer.setSsn(RandomUtil.generateRandomSSN());

		log.info("Creating a a home and work address");
		Address homeAddress = generateAddress("home", true);
		Address workAddress = generateAddress("work", false);
		
		List<Address> addressList = new ArrayList<>();
		addressList.add(homeAddress); addressList.add(workAddress);
		customer.setAddresses(addressList);

		log.info("Generating an email ...");
		Email email1 = generateEmail(customer.getFullName(), "work", true);
		Email email2 = generateEmail(customer.getFullName(), "home", false);
		List<Email> emailList = new ArrayList<>();
		emailList.add(email1); emailList.add(email2);
		customer.setEmails(emailList);
		
		log.info("Generating phone numbers ...");
		Phone phone1 = generatePhone("mobile", true);
		Phone phone2 = generatePhone("work", false);
		List<Phone> phoneList = new ArrayList<>();
		phoneList.add(phone1); phoneList.add(phone2);
		customer.setPhoneNumbers(phoneList);

		String dateString = DateUtils.getRandomDateString();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		customer.setDob(format.parse(dateString));
		
		CustomerNotes notes = new CustomerNotes();
		customer.setNotes(notes.getCustomerNotes());
		customer.setLastUpdated(DateUtils.getNow());
		
		log.info("Complete - lets return customer");
		return customer;
	}

	//NeedToDo - Generate a phone
	private Phone generatePhone(String type, boolean defaultInd) {
		log.info("Creating a phone with type {} and defaultInd {}", type , defaultInd);
		return null;
	}


	private Email generateEmail(String fullName, 
			String emailAddressType, boolean defaultInd) {
		log.info("Generating email ...");
		Email email = new Email();
		email.setId(RandomUtil.generateRandom(999999));
		email.setDefaultInd(defaultInd);
		email.setLastUpdated(DateUtils.getNow());
		email.setEmailAddress(fullName + DataTypeGenerator.getRandomDataType("EMAIL_PROVIDERS"));
		email.setEmailType(emailAddressType);
		
		return email;
	}


	private Address generateAddress(String addressType, boolean defaultInd) {
		log.info("Generating an address");
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
		log.info("Generating an order");
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
		log.info("Generating an order");

		Order order = generateOrder1(orderNumber);
		List<OrderDetail> orderDetailList = getOrderDetails1(orderNumber);
		order.setOrderDetailsList(orderDetailList);
		return order;
	}
	
	public OrderDetail generateOrderDetail(String orderNumber) {
		log.info("Creating a order detail");

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderNumber(orderNumber);
		orderDetail.setOrderDetailNumber(RandomUtil.generateRandomNumeric(5).toString());
		orderDetail.setShippedDate(DateUtils.getNow());
		
		orderDetail.setCancelOrderDetail(false);
		orderDetail.setLastUpdated(DateUtils.getNow());
		return orderDetail;
	}
	
	private CustomerType generateCustomerType() {
		log.info("Generating a customer type");

		CustomerType customerType = new CustomerType();

		customerType.setName(DataTypeGenerator.getRandomDataType("CUSTOMER_TYPE"));
		customerType.setDescription(DataTypeGenerator.getRandomDataType("CUSTOMER_TYPE"));
		customerType.setLastUpdated(DateUtils.getNow());
		return customerType;
	}

	public Product generateProduct() {
		log.info("Generating a random product");
		return generateProduct("PRD" + RandomUtil.generateRandomNumeric(5).toString());
	}
	
	public Product generateProduct(String productNumber) {
		log.info("Generating a product with productNumber {}", productNumber);
		List<String> valueList = dataTypePropertiesMap.get("PRODUCT_NAMES");
		RandomUtil.setSeed();
		int randomNumber = RandomUtil.getRandomNumberInRange(0, valueList.size());
		
		Product product = new Product();
		product.setId(RandomUtil.generateRandomNumeric(5).intValue());
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
		log.info("Generating a variable price product");

		Product product = generateProduct();
		product.setFixedPrice(false);
		
		double price = (double) priceManager.getVariablePrice(product);
		product.setPrice(price);
		
		return product;
	}
	
	public SKU generateSkuColor() {
		log.info("Generating a Sku Color");

		String skuNumber = "SKU" + RandomUtil.generateRandomAlphaString(5);
		return generateSkuColor(skuNumber);
	}

	public SKU generateSkuColor(String skuNumber) {
		log.info("Generating a Sku Color");

		SKU sku = new SKU();
		
		sku.setId(RandomUtil.generateRandomNumeric(4).intValue());
		sku.setSkuNumber(skuNumber);
		sku.setSkuType("COLOR");
		sku.setSkuDescription("Color of Product");
		sku.setSkuName("Color");
		sku.setValue(DataTypeGenerator.getRandomDataType("COLOR"));	
		sku.setLastUpdated(DateUtils.getNow());
		return sku;
	}
	
	public SKU generateSkuSize(String skuNo) {
		log.info("Generating a Sku Size");

		SKU sku = new SKU();
		log.info("Lets set up the SKU ...");
		sku.setId(RandomUtil.generateRandomNumeric(6).intValue());
		sku.setSkuNumber(skuNo);
		log.info("The Sku is size");
		sku.setSkuType("SIZE");
		sku.setSkuDescription("Size of product");
		sku.setSkuName("Size");
		sku.setValue(DataTypeGenerator.getRandomDataType("SIZE"));
		sku.setLastUpdated(DateUtils.getNow());
		return sku;
	}

	public SKU generateSkuSize() {
		log.info("Generating a Sku Size");

		String skuNumber = RandomUtil.generateRandomAlphaString(5);
		return generateSkuSize(skuNumber);
	}

	public SKU generateRandomSku() {
		log.info("Generating a Random Sku");
		RandomUtil.setSeed();
		String skuNumber = RandomUtil.generateRandomAlphaString(5);
		return generateRandomSku(skuNumber);
	}

	public SKU generateRandomSku(String skuNumber) {
		log.info("Generating a Random Sku");
		RandomUtil.setSeed();
		int x = RandomUtil.generateRandom(2);
		if (x % 2 == 0) {
			return generateSkuSize(skuNumber);
		} else {
			return generateSkuColor(skuNumber);
		}
	}
}