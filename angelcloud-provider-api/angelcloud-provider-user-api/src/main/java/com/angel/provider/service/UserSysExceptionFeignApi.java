package com.angel.provider.service;

import com.angel.base.constant.ServerResponse;
import com.angel.base.constant.ServiceNameConstants;
import com.angel.provider.model.dto.GlobalExceptionLogDto;
import com.angel.provider.service.hystrix.UserSysExceptionFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 异常feign
 * @Author: Angel
 * @Date: 2018/10/17.
 * @Description:
 */
@FeignClient(contextId = "userSysExceptionFeignApi", value = ServiceNameConstants.USER_SERVICE,
        fallback = UserSysExceptionFeignHystrix.class)
public interface UserSysExceptionFeignApi {

    /**
     * 插入异常日志
     * @param globalExceptionLogDto 全局异常DTO
     * @return ServerResponse
     */
    @PostMapping(value = "/oss/exception/insertSendExceptionLog")
    ServerResponse insertSendExceptionLog(@RequestBody GlobalExceptionLogDto globalExceptionLogDto);
}
