package com.shoppingcart.domain;

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
}
