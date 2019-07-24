package com.huawei.cse.democustomer.entity;

public class Customer {
	private int customerId;
	private String customerName;
	private String customerLevel;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	@Override
	public String toString() {
		return "{" +
				"customerId=" + customerId +
				", customerName='" + customerName + '\'' +
				", customerLevel='" + customerLevel + '\'' +
				'}';
	}

	public Customer(String customerName, String customerLevel) {
		this.customerName = customerName;
		this.customerLevel = customerLevel;
	}

	public Customer(int customerId, String customerName, String customerLevel) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerLevel = customerLevel;
	}
}
