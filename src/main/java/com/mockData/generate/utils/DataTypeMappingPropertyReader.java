package com.mockdata.generate.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.shoppingcart.exception.ShoppingCartException;

public class DataTypeMappingPropertyReader {

	private static Map<String, List<String>> dataMappingPropertiesMap = new HashMap<>();

	static {
        try {
            Properties dataTypeMappingProperties = new Properties();
            InputStream myPropertiesInputStream = DataTypeMappingPropertyReader.class.getResourceAsStream("/DataMapping.properties");
            dataTypeMappingProperties.load(myPropertiesInputStream);
            loadDataMappingPropertiesMap(dataTypeMappingProperties);
        } catch (IOException e) {
        	throw new ShoppingCartException(e);
        }
	}

	//Private Constructor to avoid initiation
	private DataTypeMappingPropertyReader() {
    }

	public static Map<String, List<String>> getDataTypePropertiesMap() {
		return dataMappingPropertiesMap;
	}

	@SuppressWarnings("rawtypes")
	private static void loadDataMappingPropertiesMap(Properties dataTypeMappingProperties) {
		Set<Object> keySet= dataTypeMappingProperties.keySet();
		Iterator iter = keySet.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			List<String> dataTypeMappings = getAsList(dataTypeMappingProperties.getProperty(key), ",");
			dataMappingPropertiesMap.put(key, dataTypeMappings);
		}
	}

	private static List<String> getAsList(String string, String separator) {
		if (!("").equalsIgnoreCase(string) && string != null) {
			String[] splitArray = string.split(separator);
			return Arrays.asList(splitArray);
		}
		return new ArrayList<>();
	}
}
