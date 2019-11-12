package com.angel.security.component;

import com.angel.security.exception.CustomOauthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * 异常处理,重写oauth 默认实现
 * @Author angel
 * @Date 19-11-12
 */
@Slf4j
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new CustomOauthException(e.getMessage()));
    }
}
