package com.angel.provider.service.hystrix;

import com.angel.base.constant.ServerResponse;
import com.angel.provider.model.vo.SysUserVo;
import com.angel.provider.service.UserSysUserFeignApi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Angel
 * @Date: 2018/9/23.
 * @Description:
 */
@Component
public class UserSysUserFeignHystrix implements UserSysUserFeignApi {
    @Override
    public ServerResponse<SysUserVo> getUserVo(@RequestParam(name = "userId") Integer userId) {
        return null;
    }
}
