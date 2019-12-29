package com.angel.provider.service.hystrix;

import com.angel.base.constant.SecurityConstants;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.provider.model.dto.UserInfo;
import com.angel.provider.model.vo.SysUserVo;
import com.angel.provider.service.UserSysUserFeignApi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990002.code(), ErrorCodeEnum.GL99990002.msg());
    }

    @Override
    public ServerResponse<UserInfo> info(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM) String from) {
        return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.GL99990002.code(), ErrorCodeEnum.GL99990002.msg());
    }
}
