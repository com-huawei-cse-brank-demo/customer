package com.huawei.cse.democustomer.constant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Response implements Serializable {
	/**
	 *
	 */

	// Retcode
	public static final int ORDER_SUCCESS = 0;

	public static final int FREE_SPACE_NOT_ENOUGH = 1;

	public static final int QUOTA_NOT_ENOUGH = 2;

	public static final int NO_AUTH = 3;

	public static final int FREE_SUCCESS = 4;

	public static final int COSTOMER_RESOURCE_SUCCESS = 5;

	public static final int NOT_FOUND = 6;

	public static final int WRONG_CREATE = 7;

	public static final int CREATE_SUCCESS = 8;




	public static final Map<Integer,String> RESPONSE_INFO = new HashMap<>();


	static {
		RESPONSE_INFO.put(ORDER_SUCCESS,"申购ECS成功");
		RESPONSE_INFO.put(FREE_SPACE_NOT_ENOUGH,"空闲的ECS资源不足");
		RESPONSE_INFO.put(QUOTA_NOT_ENOUGH,"用户配额不足");
		RESPONSE_INFO.put(NO_AUTH,"权限不足，该ECS您无权限释放");
		RESPONSE_INFO.put(FREE_SUCCESS,"释放成功");
		RESPONSE_INFO.put(COSTOMER_RESOURCE_SUCCESS,"查询成功");
		RESPONSE_INFO.put(NOT_FOUND,"用户不存在");
		RESPONSE_INFO.put(WRONG_CREATE,"创建失败");
		RESPONSE_INFO.put(CREATE_SUCCESS,"创建成功");
	}

}
