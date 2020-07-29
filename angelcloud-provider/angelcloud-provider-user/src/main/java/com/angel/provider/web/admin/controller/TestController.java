package com.angel.provider.web.admin.controller;

import com.angel.security.annotation.Inner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Angel
 * @Date: 2018/8/24.
 * @Description:
 */
@RestController
public class TestController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/")
    public String home() {
        return "Hello world";
    }

    @Inner(false)
        @GetMapping("/service")
    public List<String> serviceUrl() {
        Optional<URI> uri = discoveryClient.getInstances("angelcloud-provider-user").stream()
                .findFirst()
                .map(si -> si.getUri());
        List<String> list = discoveryClient.getServices();
        return list;
    }
}
