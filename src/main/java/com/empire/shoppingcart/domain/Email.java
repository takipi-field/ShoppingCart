package com.empire.shoppingcart.domain;

public class Email extends BaseDomain {

	private String emailAddress;
	private boolean defaultInd;
	private String emailType;

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public boolean isDefaultInd() {
		return defaultInd;
	}
	public void setDefaultInd(boolean defaultInd) {
		this.defaultInd = defaultInd;
	}
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
}