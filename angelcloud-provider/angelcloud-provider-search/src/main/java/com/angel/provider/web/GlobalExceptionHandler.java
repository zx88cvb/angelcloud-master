package com.angel.provider.web;

import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.exception.BusinessException;
import com.angel.provider.model.dto.GlobalExceptionLogDto;
import com.angel.provider.service.UserSysExceptionFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;

/**
 * 全局异常拦截器
 * @Author: Angel
 * @Date: 2018/10/17.
 * @Description:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Resource
    private TaskExecutor taskExecutor;

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private UserSysExceptionFeignApi userSysExceptionFeignApi;

    /**
     * 参数非法异常
     * @param e the e
     * @return ServerResponse
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse illegalArgumentException(IllegalArgumentException e) {
        log.error("参数非法异常={}", e.getMessage(), e);
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990100.code(), e.getMessage());
    }

    /**
     * 业务异常.
     *
     * @param e the e
     *
     * @return the wrapper
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponse businessException(BusinessException e) {
        log.error("业务异常={}", e.getMessage(), e);
        return ServerResponse.createByErrorCodeMessage(e.getCode() == 0 ? ResponseCode.SERVER_ERROR.getCode() : e.getCode(), e.getMessage());
    }

    /**
     * 全局异常
     * @param e the e
     * @return ServerResponse
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServerResponse exception(Exception e) {
        log.info("保存全局异常信息 ex={}", e.getMessage(), e);
        taskExecutor.execute(()->{
            GlobalExceptionLogDto globalExceptionLogDto = new GlobalExceptionLogDto().getGlobalExceptionLogDto(e, profile, applicationName);
            userSysExceptionFeignApi.insertSendExceptionLog(globalExceptionLogDto);
        });
        return ServerResponse.createByError();
    }
}