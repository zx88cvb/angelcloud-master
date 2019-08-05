package com.angel;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@SpringCloudApplication
@EnableFeignClients
@EnableTransactionManagement
public class AngelCloudSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngelCloudSearchApplication.class, args);
	}
}
