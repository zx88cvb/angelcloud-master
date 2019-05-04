package com.angel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@SpringCloudApplication
@EnableFeignClients
@EnableTransactionManagement
@EnableScheduling	// 开启定时任务
public class AngelCloudBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngelCloudBlogApplication.class, args);
	}
}
