package com.shoppingcart.workflow.tasks;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockData.generate.utils.RandomUtil;
import com.shoppingcart.exception.ShoppingCartException;
import com.shoppingcart.util.ExceptionUtils;
import com.shoppingcart.util.ShoppingCartPropertyReader;
import com.shoppingcart.util.exception.SkuException;
import com.shoppingcart.workflow.ShoppingCartWF;

public class ShoppingCartTask implements Runnable
{
	private final static Logger log = LoggerFactory.getLogger(ShoppingCartTask.class);
	
	@Override
	public void run()
	{
		try {
			executeMultipleWorkflows();
		} catch (SkuException e) {
			log.error("SkuException in executing workflows " + e.getMessage());
		} catch (ParseException e) {
			log.error("Parse Exception in thread: " + ExceptionUtils.convertStackTraceToString(e));
		} catch (NullPointerException e) {
			log.error("Null pointer exception in thread: " + ExceptionUtils.convertStackTraceToString(e));
		} catch (ClassCastException e) {
			log.error("Class cast exception in thread: " + ExceptionUtils.convertStackTraceToString(e));				
		} catch (ShoppingCartException e) {
			log.error("Exception in thread: " + ExceptionUtils.convertStackTraceToString(e));
		} catch (Exception e) {
			log.error("Unexpected exception in thread: " + ExceptionUtils.convertStackTraceToString(e));
		}
	}
	
	private void executeMultipleWorkflows() throws ParseException {
		log.info("Starting executing multiple workflows");
		ShoppingCartWF workflow = new ShoppingCartWF();
		
		boolean workflow1Enabled = new Boolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW1.ENABLED")).booleanValue();
		boolean workflow2Enabled = new Boolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW2.ENABLED")).booleanValue();
		boolean workflow3Enabled = new Boolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW3.ENABLED")).booleanValue();
		boolean workflow4Enabled = new Boolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW4.ENABLED")).booleanValue();
		boolean workflow5Enabled = new Boolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW5.ENABLED")).booleanValue();
		boolean workflow6Enabled = new Boolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW6.ENABLED")).booleanValue();
		boolean workflow7Enabled = new Boolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW7.ENABLED")).booleanValue();
		boolean workflow8Enabled = new Boolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW8.ENABLED")).booleanValue();
		boolean workflow9Enabled = new Boolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW9.ENABLED")).booleanValue();
		boolean workflow10Enabled = new Boolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW10.ENABLED")).booleanValue();
		boolean workflow11Enabled = new Boolean(ShoppingCartPropertyReader.getInstance().getProperty("SHOPPING_CART.WORKFLOW11.ENABLED")).booleanValue();

		
		int randomNo = RandomUtil.getRandomNumberInRange(1, 12);
		switch (randomNo) {
			case 1:	if (workflow1Enabled) {	
						workflow.workflow1();
					}
					break;
			case 2: if (workflow2Enabled) {	
						workflow.workflow2();
					}
			break;
			case 3: if (workflow3Enabled) {	
						workflow.workflow3();
					}
					break;
			case 4: if (workflow4Enabled) {	
						workflow.workflow4();
					}
					break;
			case 5:	if (workflow5Enabled) {	
						workflow.workflow5();
					}
					break;
			case 6:	if (workflow6Enabled) {	
						workflow.workflow6();
					}
					break;
			case 7:	if (workflow7Enabled) {	
						workflow.workflow7();
					}
					break;
			case 8:	if (workflow8Enabled) {	
						workflow.workflow8();
					}
					break;
			case 9: if (workflow9Enabled) {	
						workflow.workflow9();
					}
					break;
			case 10: if (workflow10Enabled) {	
						workflow.workflow10();
					}
					break;
			case 11: if (workflow11Enabled) {	
						workflow.workflow11();
					}
					break;		
			default: 	throw new ShoppingCartException("Could not find workflow to initiate: " + randomNo);
		}
	}
}
