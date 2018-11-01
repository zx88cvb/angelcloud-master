package com.angel;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@SpringCloudApplication
@EnableFeignClients
@EnableTransactionManagement
public class AngelCloudAdGroupApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngelCloudAdGroupApplication.class, args);
	}
}
