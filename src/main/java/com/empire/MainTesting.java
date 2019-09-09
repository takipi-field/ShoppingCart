package com.empire;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empire.mockdata.generate.DelayGenerator;
import com.empire.mockdata.generate.utils.RandomUtil;
import com.empire.shoppingcart.domain.ShoppingCartProperties;
import com.empire.shoppingcart.manager.ShoppingCartPropertyManager;
import com.empire.shoppingcart.thirdpartyutils.URLConnectionHandler;

public class MainTesting {

	public final static Logger log = LoggerFactory.getLogger(MainTesting.class);

	public static void main(String[] args) {
		log.info("Starting Retail Application ...Waiting for 15 seconds for OverOps to Initialize");
		DelayGenerator.introduceDelay(15000);

		ShoppingCartProperties scProperties = ShoppingCartPropertyManager.populate(args);

		for (int i = 1; i <= 10; i++) {
//			ShoppingCartWF workflow = new ShoppingCartWF();
//			workflow.workflow14();
			
			String orderNumber = "ORD-" + RandomUtil.generateRandomNumericString(4);
			String customerNumber = "CUST-" + RandomUtil.generateRandomNumericString(4);
			
			log.info("CustomerNumber {}", customerNumber);
			log.info("OrderNumber {}", orderNumber);

			String connectString = scProperties.getUrlConnectString();
			
			URLConnectionHandler handler = new URLConnectionHandler();
			handler.handle(customerNumber, orderNumber, connectString);
		}

	}
}