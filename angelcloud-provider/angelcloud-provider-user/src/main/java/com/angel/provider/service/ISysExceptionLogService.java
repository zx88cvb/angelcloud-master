package com.angel.provider.service;

import com.angel.provider.model.dto.GlobalExceptionLogDto;

/**
 * @Author: Angel
 * @Date: 2018/10/17.
 * @Description:
 */
public interface ISysExceptionLogService {

    /**
     * 插入异常日志
     * @param globalExceptionLogDto 异常日志DTO
     */
    void insertSendExceptionLog(GlobalExceptionLogDto globalExceptionLogDto);
}
