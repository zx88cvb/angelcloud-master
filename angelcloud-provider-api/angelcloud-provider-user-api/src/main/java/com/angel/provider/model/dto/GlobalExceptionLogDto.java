package com.angel.provider.model.dto;

import com.angel.base.constant.GlobalConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Arrays;
import java.util.Date;

/**
 * 全局异常类DTO
 * @Author: Angel
 * @Date: 2018/10/17.
 * @Description:
 */
@Data
@NoArgsConstructor
@Slf4j
public class GlobalExceptionLogDto {
    /**
     * 运行环境
     */
    private String profile;

    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 异常信息(通过exception.getMessage()获取到的内容)
     */
    private String exceptionMessage;

    /**
     * 异常原因(通过exception.getCause()获取到的内容)
     */
    private String exceptionCause;

    /**
     * 异常类型
     */
    private String exceptionSimpleName;

    /**
     * 异常堆栈信息
     */
    private String exceptionStack;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人ID
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 获取全局异常DTO
     * @param ex 异常
     * @param profile 运行环境
     * @param applicationName 运行服务名称
     * @return 返回当前异常DTO
     */
    public GlobalExceptionLogDto getGlobalExceptionLogDto(Exception ex, String profile, String applicationName) {
        String message = ex.getMessage();
        if (StringUtils.isNotBlank(message) && message.length() > GlobalConstant.EXCEPTION_MESSAGE_MAX_LENGTH) {
            this.exceptionMessage = StringUtils.substring(message, 0, GlobalConstant.EXCEPTION_MESSAGE_MAX_LENGTH) + "...";
        }
        this.exceptionSimpleName = ex.getClass().getSimpleName();
        String cause = ex.getCause() == null ? null : ex.getCause().toString();
        if (StringUtils.isNotBlank(cause) && cause.length() > GlobalConstant.EXCEPTION_CAUSE_MAX_LENGTH) {
            this.exceptionCause = StringUtils.substring(cause, 0, GlobalConstant.EXCEPTION_CAUSE_MAX_LENGTH) + "...";
        }
        this.exceptionStack = Arrays.toString(ex.getStackTrace());

        this.profile = profile;

        //TODO 获取登录人
        /*LoginAuthDto loginAuthDto = null;

        try {
            loginAuthDto = (LoginAuthDto) ThreadLocalMap.get(GlobalConstant.Sys.TOKEN_AUTH_DTO);
        } catch (Exception e) {
            log.error("获取登陆人信息, 出现异常={}", e.getMessage(), e);
        }

        if (loginAuthDto == null) {
            loginAuthDto = new LoginAuthDto(-1L, "SYSTEM_TASK", "系统任务");
        }
        this.creatorId = loginAuthDto.getUserId();
        this.creator = loginAuthDto.getUserName();*/
        this.applicationName = applicationName;
        return this;
    }
}
