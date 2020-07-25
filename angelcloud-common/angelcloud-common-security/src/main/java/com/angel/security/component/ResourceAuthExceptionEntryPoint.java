package com.angel.security.component;

import cn.hutool.http.HttpStatus;
import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.ServerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * 客户端异常处理 {@link org.springframework.security.core.AuthenticationException } 不同细化异常处理
 * @Author angel
 * @Date 2020/7/25
 */
@Slf4j
@Component
@AllArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding(GlobalConstant.Sys.UTF8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String message = authException.getMessage();
        if (authException instanceof CredentialsExpiredException
                || authException instanceof InsufficientAuthenticationException) {
            String msg = SpringSecurityMessageSource.getAccessor().getMessage(
                    "AbstractUserDetailsAuthenticationProvider.credentialsExpired", authException.getMessage(),
                    Locale.CHINA);
            message = msg;
        }

        if (authException instanceof UsernameNotFoundException) {
            String msg = SpringSecurityMessageSource.getAccessor().getMessage(
                    "AbstractUserDetailsAuthenticationProvider.noopBindAccount", authException.getMessage(),
                    Locale.CHINA);
            message = msg;
        }

        ServerResponse<String> serverResponse = ServerResponse.createByErrorMessage(message);

        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(serverResponse));
    }
}
