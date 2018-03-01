package com.shoppingcart.domain;

import java.util.Date;

public class BaseDomain {

	private int id;
	private Date lastUpdated;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
