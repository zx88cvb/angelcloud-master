package com.angel.provider.security.handler;

import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义token校验信息
 * @Author angel
 * @Date 19-11-12
 */
@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

    /**
     * 自定义EntryPoint用于tokan校验失败返回信息
     * @param httpServletRequest request
     * @param httpServletResponse Response
     * @param e e
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //返回json形式的错误信息
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");

        ServerResponse<Object> response =
                ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990403.code(),
                        ErrorCodeEnum.GL99990403.msg());
        httpServletResponse.getWriter().print(response.toString());
        httpServletResponse.getWriter().flush();
    }
}
