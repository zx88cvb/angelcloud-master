package com.angel.security.exception;

import com.angel.security.component.CustomOauthExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

/**
 * 用户异常
 * @Author angel
 * @Date 2020/7/26
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class UserException extends CustomOauthException {

    @Getter
    private String errorCode;

    public UserException(String msg) {
        super(msg);
    }

    public UserException(String msg, String errorCode) {
        super(msg, errorCode);
        this.errorCode = errorCode;
    }
}
