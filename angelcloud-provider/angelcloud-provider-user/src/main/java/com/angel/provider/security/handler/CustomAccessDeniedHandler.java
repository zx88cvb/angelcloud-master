package com.angel.provider.security.handler;

import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义授权信息
 * @Author angel
 * @Date 19-11-12
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 授权失败时返回的信息
     * @param httpServletRequest request
     * @param httpServletResponse Response
     * @param e e
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //返回json形式的错误信息
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        ServerResponse<Object> response =
                ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990401.code(),
                        ErrorCodeEnum.GL99990401.msg());
        httpServletResponse.getWriter().println(response.toString());
        httpServletResponse.getWriter().flush();
    }
}
