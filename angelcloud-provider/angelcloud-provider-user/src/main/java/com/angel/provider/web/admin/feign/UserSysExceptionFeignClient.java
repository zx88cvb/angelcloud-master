package com.angel.provider.web.admin.feign;

import com.angel.base.constant.ServerResponse;
import com.angel.provider.model.dto.GlobalExceptionLogDto;
import com.angel.provider.service.ISysExceptionLogService;
import com.angel.provider.service.UserSysExceptionFeignApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 远程调用用户全局异常client
 * @Author: Angel
 * @Date: 2018/10/17.
 * @Description:
 */
@RestController
@RefreshScope
@Api("feign远程调用用户全局异常接口")
@Slf4j
public class UserSysExceptionFeignClient implements UserSysExceptionFeignApi{

    @Resource
    private ISysExceptionLogService iSysExceptionLogService;

    /**
     * 插入异常日志
     * @param globalExceptionLogDto 全局异常DTO
     * @return
     */
    @Override
    @ApiOperation(httpMethod = "POST", value = "保存异常日志")
    public ServerResponse insertSendExceptionLog(@RequestBody GlobalExceptionLogDto globalExceptionLogDto) {
        try {
            iSysExceptionLogService.insertSendExceptionLog(globalExceptionLogDto);
        } catch (Exception e) {
            log.error("insertSendExceptionLog={}", e.getMessage(), e);
        }
        return ServerResponse.createBySuccess();
    }
}
