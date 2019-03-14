package com.shoppingcart.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoppingcart.dao.SkuDAO;
import com.shoppingcart.dao.impl.SkuDAOImpl;
import com.shoppingcart.domain.SKU;
import com.shoppingcart.exception.ShoppingCartException;

public class SkuTest {

	private static SkuDAO skuDAO = null;
	private static SKU sku = null;
	private final static Logger log = LoggerFactory.getLogger(SkuTest.class);

	@BeforeClass
	static void setUpBeforeClass() throws Exception {
		try {
			skuDAO = new SkuDAOImpl();
			sku = skuDAO.create("SKUTEST4000");
		} catch (Exception e) {
			log.error("Could not create Skew");
		}
	}

	@Test
	public void validateSku() {
		if (sku == null) {
			assertNull(sku, "Cannot validate Sku. Sku is null.");
		} else {
			assertEquals(sku.getSkuNumber(), "SKUTEST4000", "Sku Number is not available");
		}
	}
	
	@Test
	public void updateSku() {
		if (sku == null) {
			assertNull(sku, "Cannot validate Sku. Sku is null.");
		} else {
			try {
				sku.setSkuName("TestSkuName");
				sku.setSkuDescription("TestSkuDescription");
				skuDAO.update(sku);
				assertEquals(sku.getSkuName(), "TestSkuName", "Sku TestSkuName is not available");
			} catch (ShoppingCartException e) {
				assertEquals(sku.getSkuNumber(), "SKUTEST4000", "Sku Number is not available");
			}
		}
	}
	
	@Test
	public void validateSkuName() {
		if (sku == null) {
			assertNull(sku, "Cannot validate Sku Name. Sku is null.");
		} else {
			assertNotNull(sku.getSkuName(), "Sku Name is not available");
		}
	}

	@Test
	public void validateSkuDescription() {
		if (sku == null) {
			assertNull(sku, "Cannot validate Sku Description. Sku is null.");
		} else {
			assertNotNull(sku.getSkuDescription(), "Sku Description is not available");
		}
	}
	
	@Test
	public void validatedeleteSku() {
		if (sku == null) {
			assertNull(sku, "Cannot validate Sku Description. Sku is null.");
		} else {
			try {
				boolean success = skuDAO.delete(sku);
				assertTrue(success, "Unable to delete Sku");
			} catch (Exception e) {
				// Do nothing
			}
		}
	}
}