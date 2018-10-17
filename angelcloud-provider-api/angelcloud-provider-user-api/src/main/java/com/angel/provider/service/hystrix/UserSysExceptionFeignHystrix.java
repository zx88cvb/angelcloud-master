package com.angel.provider.service.hystrix;

import com.angel.base.constant.ServerResponse;
import com.angel.provider.model.dto.GlobalExceptionLogDto;
import com.angel.provider.service.UserSysExceptionFeignApi;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: Angel
 * @Date: 2018/10/17.
 * @Description:
 */
public class UserSysExceptionFeignHystrix implements UserSysExceptionFeignApi {
    @Override
    public ServerResponse insertSendExceptionLog(@RequestBody GlobalExceptionLogDto globalExceptionLogDto) {
        return null;
    }
}
