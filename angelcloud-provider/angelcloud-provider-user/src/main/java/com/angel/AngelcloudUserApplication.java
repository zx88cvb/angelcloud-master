package com.angel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AngelcloudUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngelcloudUserApplication.class, args);
	}
}
