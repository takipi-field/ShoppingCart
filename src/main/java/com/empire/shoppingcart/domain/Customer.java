package com.empire.shoppingcart.domain;

import java.util.Date;
import java.util.List;

public class Customer extends BaseDomain {

	private String accountNumber;
	private CustomerType customerType;

	private String firstName;
	private String lastName;
	private Date dob;
	private String ssn;
	private List<Phone> phoneNumbers;
	private List<Address> addresses;
	private List<Email> emails;
	private List<CreditCard> creditCards;
	
	private Date createdDateTime;
	private Order lastOrder;
	
	private float totalSpent;
	private boolean active;
	
	private String notes;

	public Phone getDefaultPhone() {
		for (Phone phone : phoneNumbers) {
			if (phone.isDefaultInd())
				return phone;
		}
		return null;
	}

	public Address getDefaultAddress() {
		for (Address address : addresses) {
			if (address.isDefaultInd())
				return address;
		}
		return null;
	}

	public Email getDefaultEmail() {
		for (Email email : emails) {
			if (email.isDefaultInd())
				return email;
		}
		return null;
	}
	
	public CreditCard getDefaultCreditCard() {
		for (CreditCard creditCard : creditCards) {
			if (creditCard.isDefaultInd())
				return creditCard;
		}
		return null;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<Phone> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<Phone> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Order getLastOrder() {
		return lastOrder;
	}

	public void setLastOrder(Order lastOrder) {
		this.lastOrder = lastOrder;
	}

	public float getTotalSpent() {
		return totalSpent;
	}

	public void setTotalSpent(float totalSpent) {
		this.totalSpent = totalSpent;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}