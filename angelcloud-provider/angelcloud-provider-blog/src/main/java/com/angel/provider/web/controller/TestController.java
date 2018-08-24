package com.angel.provider.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Angel
 * @Date: 2018/8/24.
 * @Description:
 */
@RestController
public class TestController {
    @GetMapping("/")
    public String home() {
        return "Hello world";
    }
}
