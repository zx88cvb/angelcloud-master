package com.angel.security.exception;

import com.angel.security.component.CustomOauthExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义异常
 * @Author angel
 * @Date 19-11-12
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
    @Getter
    private String errorCode;

    public CustomOauthException(String msg) {
        super(msg);
    }

    public CustomOauthException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
