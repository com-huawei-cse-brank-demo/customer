package com.huawei.cse.democustomer;

import org.apache.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableServiceComb
@SpringBootApplication
public class DemoCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCustomerApplication.class, args);
    }

}
