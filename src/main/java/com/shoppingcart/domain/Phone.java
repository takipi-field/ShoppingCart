package com.shoppingcart.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Phone extends BaseDomain {

	private String phoneNumber;
	private String phoneType;
	private boolean defaultInd;

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public boolean isDefaultInd() {
		return defaultInd;
	}
	public void setDefaultInd(boolean defaultInd) {
		this.defaultInd = defaultInd;
	}
	
	public boolean isValid() {
		String regex = "^\\+?[0-9. ()-]{10,25}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (this.phoneNumber != null)
        		return matcher.matches();
        return false;
	}
}
