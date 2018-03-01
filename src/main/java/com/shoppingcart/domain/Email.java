package com.shoppingcart.domain;

import java.util.regex.Pattern;
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

	 public boolean isValid() {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
		                    "[a-zA-Z0-9_+&*-]+)*@" +
		                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
		                    "A-Z]{2,7}$";
		                     
		Pattern pat = Pattern.compile(emailRegex);
		if (emailAddress == null)
		    return false;
		return pat.matcher(emailAddress).matches();
	 }
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
}