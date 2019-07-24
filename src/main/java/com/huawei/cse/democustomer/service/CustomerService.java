package com.huawei.cse.democustomer.service;

import com.huawei.cse.democustomer.entity.Customer;
import com.huawei.cse.democustomer.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
	@Autowired
	private CustomerMapper customerMapper;

	public Customer queryCustomerByCustomerId(int customer_id){
		return customerMapper.queryCustomerByCustomerId(customer_id);
	}

	public Customer queryCustomerByCustomerName(String name){
		return customerMapper.queryCustomerByCustomerName(name);
	}

	public List<Customer> queryCustomerByCustomerLevel(String level){
		return customerMapper.queryCustomerByLevel(level);
	}

	public int createCustomer(int customerId, String name) {
		if(this.queryCustomerByCustomerId(customerId)!=null) return 0;

		Customer customer = new Customer(customerId,name,"V0");
		customerMapper.save(customer);
		return customer.getCustomerId();
	}
}
