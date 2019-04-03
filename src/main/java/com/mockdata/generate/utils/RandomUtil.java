package com.mockdata.generate.utils;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;

/**Does not create a static class as you must utilize a single seed in subsequent
 * calls to insure unique randomness
 * 
 */
public class RandomUtil {
    private static SecureRandom hdrRndm = new SecureRandom();

	/**Returns a string of randomly generate alpha numeric characters. This string
	 * will be the length specified by the input parameter. 
	 * @param length the total length of random characters you wish returned
	 * @return <b>string</b> a randomly generated string of alphanumeric characters only
	 */
    private RandomUtil() {
    	// 
    }
    
    public static void setSeed() {
    	hdrRndm.setSeed(System.currentTimeMillis());
    }
    
    public static Integer generateRandomNumber() {
    	return RandomUtils.nextInt();
    }
    
    public static int getRandomNumberInRange(int low, int high) {
    	return hdrRndm.nextInt(high-low) + low;
    }

    public static int generateRandom(int length){
    	return hdrRndm.nextInt(length);
    }
    
    public static BigDecimal generateRandomDecimal(int length){
    	if(length == 0){
    		return null;
    	}
    	String sNums = generateRandomNumericString(length);
    	String sNums2 = generateRandomNumericString(2);
    	sNums = sNums + "." + sNums2;
    	return new BigDecimal(sNums);
    }
    
    public static Long generateRandomNumeric(int length) {
    	if(length == 0){
    		return null;
    	}
    	String sNums = generateRandomNumericString(length);
    	return new Long(sNums);
    }
    public static String generateRandomAlphaString(int strlength){
    	if(strlength == 0){
    		return null;
    	}
    	StringBuilder returnVal = new StringBuilder();
    	String[] vals = {"a","b","c","d","e","f","g","h","i","j","k","l","m",
    			"n","o","p","q","r","s","t","u","v","w","x","y","z"};
    	for(int lp = 0;lp < strlength; lp++){
    		returnVal.append(vals[generateRandom(26)]);
    	}
    	return returnVal.toString();
    }
    public static String generateRandomNumericString(int strlength) {
    	if(strlength == 0){
    		return null;
    	}
    	StringBuilder returnVal = new StringBuilder();
    	String[] vals = {"1","2","3","4","5","6","7","8","9", "0"};
    	for(int lp = 0;lp < strlength; lp++){
    		returnVal.append(vals[generateRandom(10)]);
    	}
    	return returnVal.toString();
    }
    
    public static Integer generateRandomNumbers(int length){
    	if(length == 0){
    		return null;
    	}
    	String sNums = generateRandomNumericString(length);
    	return new Integer(sNums);
    }
    
    public static Object getRandomObjectFromList(@SuppressWarnings("rawtypes") List objList){
		if (objList == null || objList.isEmpty()) {
			return null;
		} else if (objList.size() == 1) {
			return objList.get(0);
		}
		int lGetObjIndex = generateRandom(objList.size());
		return objList.get(lGetObjIndex);
    }
    public static String getAlphaNumericRandom(int length) {
		String[] mapOfCharacters = getCharacterMap();
    	StringBuilder sRandomString = new StringBuilder();

		// Now lets return the number of characters requested
		for (int j = 0; j < length; j++) {
			int rndm = hdrRndm.nextInt(61) + 0;
			String sItem = mapOfCharacters[rndm];
			sRandomString.append(sItem);
		}
		return sRandomString.toString();
	}

	private static String[] getCharacterMap() {
		String[] universeValues = new String[62];
		int asciiAlpha = 65; // The start of the alpha ascii character set

		// Add the numbers
		for (int i = 0; i < 62; i++) {
			if (i < 10) {
				// numbers zero through 9
				universeValues[i] = Integer.toString(i);
			} else {
				universeValues[i] = Character.toString((char) asciiAlpha);
				// 91 - 96 are not alpha characters in the ascii map
				if (asciiAlpha + 1 == 91) {
					asciiAlpha = 97;
				} else {
					asciiAlpha = asciiAlpha + 1;
				}
			}
		}
		return universeValues;
	}

	public static String generateRandomSSN() {
		return generateRandom(999) + "-" + generateRandom(99) + "-" + generateRandom(9999);
	}
}