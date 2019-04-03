package com.mockdata.generate;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.utils.DataTypeMappingPropertyReader;
import com.mockdata.generate.utils.RandomUtil;
import com.shoppingcart.exception.ShoppingCartException;

public class DataTypeGenerator {

	public static final Logger log = LoggerFactory.getLogger(DataTypeGenerator.class);
	private static Map<String, List<String>> dataTypePropertiesMap = 
			DataTypeMappingPropertyReader.getDataTypePropertiesMap();

	private DataTypeGenerator() {
		//Private constructor to avoid instantiation
	}

	public static String getRandomDataType(String key) {
		List<String> valueList = dataTypePropertiesMap.get(key);
		if (valueList == null) {
			throw new ShoppingCartException("Could not find dataMapping for key: " + key);
		}
		int randomNumber = RandomUtil.getRandomNumberInRange(0, valueList.size());
		return valueList.get(randomNumber);
	}

	public static String getRandomDataType(String key, int randomNumber) {
		List<String> valueList = dataTypePropertiesMap.get(key);
		if (valueList == null) {
			log.error("Data Type is null");
			throw new ShoppingCartException("Could not find dataMapping for key: " + key);
		}
		return valueList.get(randomNumber);
	}
}
