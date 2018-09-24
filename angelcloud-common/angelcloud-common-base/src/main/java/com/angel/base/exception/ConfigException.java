package com.angel.base.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 配置异常
 *
 * @author Angel
 * Created by Administrator on 2018/8/1.
 */
@Slf4j
public class ConfigException extends RuntimeException {

    /**
     * 无参构造
     */
    public ConfigException() {
    }

    /**
     * 带消息构造
     * @param message 错误信息
     */
    public ConfigException(String message) {
        super(message);
    }
}
