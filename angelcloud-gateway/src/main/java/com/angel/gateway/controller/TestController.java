package com.angel.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author angel
 * @Date 19-5-13
 */
@RestController
public class TestController {
    @GetMapping("/")
    public String home() {
        return "gateway";
    }
}
