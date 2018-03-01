package com.shoppingcart.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Product extends BaseDomain {

	private String productNumber;
	private String productName;
	private String productDescription;
	private String productCategory;
	private String productType;
	private long availableQuantity;
	private List<SKU> skus = new ArrayList<SKU>();
	
	private boolean fixedPrice;
	private double price;
	
	private boolean discontinued;
	private boolean backlog;
	private Date estShipDate;

	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public long getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public List<SKU> getSkus() {
		return skus;
	}
	public void setSkus(List<SKU> skus) {
		this.skus = skus;
	}
	public boolean isFixedPrice() {
		return fixedPrice;
	}
	public void setFixedPrice(boolean fixedPrice) {
		this.fixedPrice = fixedPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}
	public boolean isBacklog() {
		return backlog;
	}
	public void setBacklog(boolean backlog) {
		this.backlog = backlog;
	}
	public Date getEstShipDate() {
		return estShipDate;
	}
	public void setEstShipDate(Date estShipDate) {
		this.estShipDate = estShipDate;
	}
	public void addSku(SKU sku) {
		skus.add(sku);
	}
}
