package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.vo.SysUserVo;

/**
 * @Author: Angel
 * @Date: 2018/9/24.
 * @Description:
 */
public interface IUserSysUserService {

    /**
     * 远程调用获取userVo
     * @param userId 用户id
     * @return 返回用户Vo结果集
     */
    ServiceResult<SysUserVo> getUserVo(Integer userId);
}
