package com.angel.provider.web.admin.feign;

import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.exceptions.UserBizException;
import com.angel.provider.model.vo.SysUserVo;
import com.angel.provider.service.ISysUserService;
import com.angel.provider.service.UserSysUserFeignApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户模块远程调用
 * Author: Angel
 * Date: 2018/9/23.
 * Description:
 */
@RestController
@Api("feign远程调用用户接口")
public class UserSysUserFeignClient implements UserSysUserFeignApi{

    @Resource
    private ISysUserService iSysUserService;

    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return 返回用户结果集
     */
    @Override
    @ApiOperation(httpMethod = "GET", value = "根据用户id获取用户信息")
    public ServerResponse<SysUserVo> getUserVo(@RequestParam(name = "userId") Integer userId) {

        if (userId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        ServiceResult<SysUserVo> serviceResult = iSysUserService.getUserVoById(userId);

        if (serviceResult == null) {
            throw new UserBizException(ErrorCodeEnum.GL9999404);
        }

        if (!serviceResult.isSuccess()) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(serviceResult.getResult());
    }
}
