package com.mockData.generate;

import java.util.List;
import java.util.Map;

import com.mockData.generate.utils.DataTypeMappingPropertyReader;
import com.mockData.generate.utils.RandomUtil;
import com.shoppingcart.util.exception.ShoppingCartException;

public class DataTypeGenerator {

	private static Map<String, List<String>> dataTypePropertiesMap = 
			DataTypeMappingPropertyReader.getDataTypePropertiesMap();

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
			throw new ShoppingCartException("Could not find dataMapping for key: " + key);
		}
		return valueList.get(randomNumber);
	}
}
