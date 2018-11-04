package com.angel.provider.web.controller;

import com.angel.provider.service.IAdGroupContentService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 广告内容 Controller
 * @Author: Angel
 * @Date: 2018/11/4.
 * @Description:
 */
@RestController
@RequestMapping("/adGroupContent")
@Api("广告内容Api")
public class AdGroupContentController {

    @Resource
    private IAdGroupContentService iAdGroupContentService;
}
