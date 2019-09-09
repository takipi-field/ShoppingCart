package com.empire.shoppingcart.manager;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.empire.mockdata.generate.utils.RandomUtil;
import com.empire.shoppingcart.util.CompanyNameReader;

public class ThirdPartyManager {

	public final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public String getConnectionDetails() {
		String companyName = getCompany();
		
		return "ConnectTo_" + companyName; 
	}

	private String getCompany() {
		Properties p = CompanyNameReader.getInstance();
		int i = RandomUtil.getRandomNumberInRange(1, p.size() + 1);
		String companyName = (String) p.get(i + "");
		log.debug("Getting Ship To Company Name: {} {}", i, companyName);
		
		return companyName;
	}
}
