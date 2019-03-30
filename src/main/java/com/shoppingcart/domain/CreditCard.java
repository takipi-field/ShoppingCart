package com.shoppingcart.domain;

public class CreditCard extends BaseDomain {

	private long cardNumber;
	private String expiryDate;
	private String securityCode;
	private boolean defaultInd;

	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	
	public boolean isValid() {
		return
			(getSize(cardNumber) >= 13 && getSize(cardNumber) <= 16) &&
			(prefixMatched(cardNumber, 4) || prefixMatched(cardNumber, 5) ||
			prefixMatched(cardNumber, 37) || prefixMatched(cardNumber, 6)) &&
			((sumOfDoubleEvenPlace(cardNumber) + sumOfOddPlace(cardNumber)) % 10 == 0);
	}
	
	/** Get the result from Step 2 */
	private int sumOfDoubleEvenPlace(long number) {
		int sum = 0;
		String num = number + "";
		for (int i = getSize(number) - 2; i >= 0; i -= 2) {
			sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
		}
		return sum;
	}
	
	/** Return this number if it is a single digit, otherwise,
	* return the sum of the two digits */
	private int getDigit(int number) {
		if (number < 9)
			return number;
		else
			return number / 10 + number % 10;
	}
	
	/** Return sum of odd-place digits in number */
	private int sumOfOddPlace(long number) {
		int sum = 0;
		String num = number + "";
		for (int i = getSize(number) - 1; i >= 0; i -= 2) {
			sum += Integer.parseInt(num.charAt(i) + "");
		}
		return sum;
	}
	
	/** Return true if the digit d is a prefix for number */
	private boolean prefixMatched(long number, int d) {
		return getPrefix(number, getSize(d)) == d;
	}
	
	/** Return the number of digits in d */
	private int getSize(long d) {
		String num = d + "";
		return num.length();
	}
	
	/** Return the first k number of digits from number. If the
	* number of digits in number is less than k, return number. */
	private long getPrefix(long number, int k) {
		if (getSize(number) > k)  {
			String num = number + "";
			return  Long.parseLong(num.substring(0, k));
		}
		return number;
	}
	public boolean isDefaultInd() {
		return defaultInd;
	}
	public void setDefaultInd(boolean defaultInd) {
		this.defaultInd = defaultInd;
	}
}
