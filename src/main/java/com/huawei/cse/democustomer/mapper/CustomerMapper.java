package com.huawei.cse.democustomer.mapper;

import com.huawei.cse.democustomer.entity.Customer;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.util.List;


@Mapper
public interface CustomerMapper {

	@Select("select * from t_demo_customer_l50001838 where customer_id=#{customer_id}")
	Customer queryCustomerByCustomerId(@Param("customer_id") int customer_id);

	@Insert("insert into t_demo_customer_l50001838(customer_id, customer_name, customer_level) values(#{customerId}, #{customerName},#{customerLevel})")
	@Options(useGeneratedKeys = true,keyProperty = "customerId", keyColumn = "customer_id")
	void save(Customer customer);

	@Select("select * from t_demo_customer_l50001838 where customer_name=#{customer_name}")
	Customer queryCustomerByCustomerName(@Param("customer_name") String customer_name);

	@Select("select * from t_demo_customer_l50001838 where customer_level=#{customer_level}")
	List<Customer> queryCustomerByLevel(@Param("customer_level") String customer_level);
}
