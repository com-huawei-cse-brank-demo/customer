package com.huawei.cse.democustomer.entity;

public class Resource {
	public enum Status{
		FREE,OCCUPIED
	}
	private int resourceId;
	private int customerId;
	private Status status;

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Resource{" +
			"resourceId=" + resourceId +
			", customerId=" + customerId +
			", status=" + status +
			'}';
	}


}
