package com.angel.provider.service.impl;

import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.exceptions.UserBizException;
import com.angel.provider.model.vo.SysUserVo;
import com.angel.provider.service.IUserSysUserService;
import com.angel.provider.service.UserSysUserFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Angel
 * @Date: 2018/9/24.
 * @Description:
 */
@Slf4j
@Service
public class UserSysUserServiceImpl implements IUserSysUserService {

    /**
     * 注入远程调用类
     */
    @Resource
    private UserSysUserFeignApi userSysUserFeignApi;


    @Override
    public ServiceResult<SysUserVo> getUserVo(Integer userId) {
        log.info(String.format("UserSysUserFeignApi - getUserVo  用户id:%d",userId));
        if (userId == null) {
            return ServiceResult.notFound();
        }
        ServerResponse<SysUserVo> serverResponse = userSysUserFeignApi.getUserVo(userId);

        if (serverResponse == null) {
            throw new UserBizException(ErrorCodeEnum.GL99990500);
        }

        if (!serverResponse.isSuccess()) {
            throw new UserBizException(ErrorCodeEnum.USER10011003);
        }
        return ServiceResult.of(serverResponse.getData());
    }
}
