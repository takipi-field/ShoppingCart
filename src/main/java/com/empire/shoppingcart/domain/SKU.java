package com.empire.shoppingcart.domain;

public class SKU extends BaseDomain {

	private String skuNumber;
	private String skuType;
	private String skuName;
	private String skuDescription;
	private String value;

	public String getSkuNumber() {
		return skuNumber;
	}
	public void setSkuNumber(String skuNumber) {
		this.skuNumber = skuNumber;
	}
	public String getSkuType() {
		return skuType;
	}
	public void setSkuType(String skuType) {
		this.skuType = skuType;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getSkuDescription() {
		return skuDescription;
	}
	public void setSkuDescription(String skuDescription) {
		this.skuDescription = skuDescription;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
