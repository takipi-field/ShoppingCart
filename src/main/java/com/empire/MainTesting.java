package com.empire;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empire.mockdata.generate.DelayGenerator;
import com.empire.mockdata.generate.utils.RandomUtil;
import com.empire.shoppingcart.shipping.ShippingHandler;

public class MainTesting {

	public final static Logger log = LoggerFactory.getLogger(MainTesting.class);

	public static void main(String[] args) {
		log.info("Starting Retail Application ...Waiting for 15 seconds for OverOps to Initialize");
		DelayGenerator.introduceDelay(15000);

		try {
			String orderNumber = "ORD-" + RandomUtil.generateRandomNumericString(4);
			String customerNumber = "CUST-" + RandomUtil.generateRandomNumericString(4);
			
			log.info("CustomerNumber {}", customerNumber);
			log.info("OrderNumber {}", orderNumber);

			ShippingHandler handler = new ShippingHandler();
			handler.handle(customerNumber, orderNumber);
			
//			ShippingTest test = new ShippingTest();
//			test.processShipment(customerNumber, orderNumber, "test", 0);
			
			log.info("Done ....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}