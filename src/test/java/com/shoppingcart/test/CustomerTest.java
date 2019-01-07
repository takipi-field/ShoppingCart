package com.shoppingcart.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shoppingcart.dao.CustomerDAO;
import com.shoppingcart.dao.impl.CustomerDAOImpl;
import com.shoppingcart.domain.Customer;

public class CustomerTest {

	private static CustomerDAO customerDAO = null;
	private static Customer customer = null;
	private final static Logger log = LoggerFactory.getLogger(CustomerTest.class);

	@BeforeClass
	static void setUpBeforeClass() throws Exception {
		try {
			customerDAO = new CustomerDAOImpl();
			customer = customerDAO.create("CUSTTEST3000");
		} catch (Exception e) {
			log.error("Could not create customer");
		}
	}

	@Test
	public void validateAccountNumber() {
		if (customer == null) {
			assertNull(customer, "Cannot validate account Numner. Customer is null.");
		} else {
			assertEquals(customer.getAccountNumber(), "CUSTTEST3000", "Customer Number is not available");
		}
	}

	@Test
	public void validateSSN() {
		if (customer == null) {
			assertNull(customer, "Cannot validate SSN. Customer is null.");
		} else {	
			assertTrue(NumberUtils.isDigits(customer.getSsn().substring(0, 2)), "SSN first 3 digits not numeric");
			assertTrue(NumberUtils.isDigits(customer.getSsn().substring(4, 5)), "SSN second 2 digits not numeric");
			assertTrue(NumberUtils.isDigits(customer.getSsn().substring(7, 10)), "SSN last 3 digits not numeric");
		}
	}

	@Test
	public void validateAddress() {
		if (customer == null) {
			assertNull(customer, "Cannot validate Address. Customer is null.");
		} else {
			assertNotNull(customer.getAddresses().size(), "Customer Number is not available");
		}
	}
	
	@Test
	public void validateCustomerType() {
		if (customer == null) {
			assertNull(customer, "Cannot validate CustomerType. Customer is null.");
		} else {
			assertNotNull(customer.getCustomerType().getName(), "Customer Type is not null");
		}
	}
	
	@Test
	public void validateAddressType() {
		if (customer == null) {
			assertNull(customer, "Cannot validate AddressType. Customer is null.");
		} else {
			assertNotNull(customer.getAddresses().get(0).getAddressType(), "Address Type is not null");
		}
	}
	
	@Test
	public void validateAddressCity() {
		if (customer == null) {
			assertNull(customer, "Cannot validate AddressCity. Customer is null.");
		} else {
			assertNotNull(customer.getAddresses().get(0).getCity(), "City is not null");
		}
	}
	
	@Test
	public void validateAddressZip() {
		if (customer == null) {
			assertNull(customer, "Cannot validate AddressZip. Customer is null.");
		} else {
			assertNotNull(customer.getAddresses().get(0).getZipcode(), "Zip is not null");
		}
	}
	
	@Test
	public void validateAddressState() {
		if (customer == null) {
			assertNull(customer, "Cannot validate AddressState. Customer is null.");
		} else {
			assertNotNull(customer.getAddresses().get(0).getState(), "State is not null");
		}
	}

	@Test
	public void validateAddressLine1() {
		if (customer == null) {
			assertNull(customer, "Cannot validate AddressLine1. Customer is null.");
		} else {
			assertNotNull(customer.getAddresses().get(0).getLine1(), "Line1 is not null");
		}
	}
	
	@Test
	public void validateDateOfBirth() {
		if (customer == null) {
			assertNull(customer, "Cannot validate Date Of Birth. Customer is null.");
		} else {
			assertNotNull(customer.getDob(), "DateOfBirth is not null");
		}
	}
}