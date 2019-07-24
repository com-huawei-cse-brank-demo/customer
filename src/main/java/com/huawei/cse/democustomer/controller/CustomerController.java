package com.huawei.cse.democustomer.controller;

import com.huawei.cse.democustomer.constant.Response;
import com.huawei.cse.democustomer.entity.Customer;
import com.huawei.cse.democustomer.service.CustomerService;
import com.huawei.cse.democustomer.utils.JsonResponse;
import io.swagger.annotations.ApiParam;
import org.apache.servicecomb.foundation.common.net.URIEndpointObject;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.serviceregistry.RegistryUtils;
import org.apache.servicecomb.serviceregistry.api.registry.Microservice;
import org.apache.servicecomb.serviceregistry.api.registry.MicroserviceInstance;
import org.apache.servicecomb.serviceregistry.definition.DefinitionConst;
import org.springframework.beans.factory.annotation.Autowired;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static com.huawei.cse.democustomer.constant.Response.*;


@RestSchema(schemaId = "hwclouds")
@RequestMapping(path = "hwclouds/v1")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	private String edgePrefix;

	@RequestMapping(value = "/customer/{userid}", method = RequestMethod.GET)
	public Object queryCustomer(@PathVariable("userid") int customerId) {
		Customer customer = customerService.queryCustomerByCustomerId(customerId);
		if(customer == null){
            return new JsonResponse(NOT_FOUND,RESPONSE_INFO.get(NOT_FOUND),"").toString();
        }
        return new JsonResponse(COSTOMER_RESOURCE_SUCCESS,RESPONSE_INFO.get(COSTOMER_RESOURCE_SUCCESS),customer.toString()).toString();
	}

//    @RequestMapping(value = "/customer/{userid}", method = RequestMethod.GET)
//    public Object queryCustomer(@PathVariable("userid") int customerId) {
//        Customer customer = customerService.queryCustomerByCustomerId(customerId);
//        if(customer == null){
//            return String.format("{retcode: %s, retdesc: %s}",
//                    NOT_FOUND,RESPONSE_INFO.get(NOT_FOUND));
//        }
//        return customer.toString();
//    }

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	@ResponseBody
	public Object registrationAccount(@ApiParam("用户名")@RequestParam("userName")String userName, @ApiParam("密码")@RequestParam("userPassword") String userPassword) {
        // 封装微服务调用url，访问auth微服务注册用户
		prepareEdge("api");
		String createUrl = edgePrefix+"/v1/auth/user?userName="+userName+"&userPassword="+userPassword;
		String deleteUrl = edgePrefix+"/v1/auth/users?userName="+userName+"&userPassword="+userPassword;
        System.out.println(createUrl);
        System.out.println(RegistryUtils.getServiceRegistry().getMicroservice());
		RestTemplate restTemplate = RestTemplateBuilder.create();
		String response = restTemplate.postForEntity(createUrl,null,String.class).getBody();

		JSONObject jsonObj = null;
		try {
            jsonObj = (JSONObject)(new JSONParser().parse(((JSONObject)(new JSONParser().parse(response))).get("body").toString()));
		} catch (Exception e) {
            return new JsonResponse(WRONG_CREATE,RESPONSE_INFO.get(WRONG_CREATE),"").toString();

        }

		// 解析返回值，并用注册用户的id创建customer
        int id = Integer.valueOf(jsonObj.get("message").toString());
		String success = jsonObj.get("success").toString();


        if (!success.equals("true")){
            return new JsonResponse(WRONG_CREATE,RESPONSE_INFO.get(WRONG_CREATE),"").toString();

        }


		int i = customerService.createCustomer(id,userName);
		if (i == 0){
			restTemplate.delete(deleteUrl,String.class);
            return new JsonResponse(WRONG_CREATE,RESPONSE_INFO.get(WRONG_CREATE),"").toString();

        }

//		return String.format("{retcode: %s, retdesc: %s}",
//               FREE_SUCCESS,customerService.queryCustomerByCustomerName(userName).toString());
        return new JsonResponse(CREATE_SUCCESS,RESPONSE_INFO.get(CREATE_SUCCESS),customerService.queryCustomerByCustomerName(userName).toString()).toString();

    }

	private URIEndpointObject prepareEdge(String prefix) {
		Microservice microservice = RegistryUtils.getMicroservice();
		MicroserviceInstance microserviceInstance = (MicroserviceInstance) RegistryUtils.getServiceRegistry()
				.getAppManager()
				.getOrCreateMicroserviceVersionRule(microservice.getAppId(), "edge", DefinitionConst.VERSION_RULE_ALL)
				.getVersionedCache()
				.mapData()
				.values()
				.stream()
				.findFirst()
				.get();
		URIEndpointObject edgeAddress = new URIEndpointObject(microserviceInstance.getEndpoints().get(0));
		edgePrefix = String.format("http://%s:%d/%s/auth", edgeAddress.getHostOrIp(), edgeAddress.getPort(), prefix);
		// for now
        edgePrefix = String.format("http://%s:%d/%s/auth", "39.96.37.41", edgeAddress.getPort(), prefix);
		return edgeAddress;
	}
}
