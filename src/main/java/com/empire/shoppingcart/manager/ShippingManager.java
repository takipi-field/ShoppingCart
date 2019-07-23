package com.empire.shoppingcart.manager;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empire.mockdata.generate.utils.RandomUtil;
import com.empire.shoppingcart.util.CompanyNameReader;

public class ShippingManager {

	public final Logger log = LoggerFactory.getLogger(this.getClass());
	public final static String[] CARRIER = {"FedEx", "UPS", "USPS", "DHL"};
	
	public String getShipmentDetails() {
		String companyName = getShipToCompany();
		String carrier = getCarrier();
		
		return "ShipTo_" + companyName + "_via_" + carrier; 
	}

	private String getShipToCompany() {
		Properties p = CompanyNameReader.getInstance();
		int i = RandomUtil.getRandomNumberInRange(1, p.size() + 1);
		String companyName = (String) p.get(i + "");
		log.debug("Getting Ship To Company Name: {} {}", i, companyName);
		
		return companyName;
	}
	
	private String getCarrier() {
		int i = RandomUtil.getRandomNumberInRange(0, CARRIER.length);
		return CARRIER[i];
	}
}
