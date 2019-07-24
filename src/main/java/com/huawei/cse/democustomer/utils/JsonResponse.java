package com.huawei.cse.democustomer.utils;

public class JsonResponse {
//	return String.format("{retcode: %s, retdesc: %s, resources: %s}",
//	NOT_FOUND,RESPONSE_INFO.get(NOT_FOUND),"");

	private int retcode;
	private String retdesc;
	private String resources;

	public JsonResponse(int retcode, String retdesc, String resources) {
		this.retcode = retcode;
		this.retdesc = retdesc;
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "{"+
			"retcode: " + retcode +
			", retdesc: '" + retdesc + '\'' +
			", resources: " + resources +
			'}';
	}
}
