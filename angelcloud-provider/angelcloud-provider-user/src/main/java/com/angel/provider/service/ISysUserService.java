package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.SysUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 系统用户表(后台管理) 服务类
 * </p>
 *
 * @author aa
 * @since 2018-07-30
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 返回结果集
     */
    ServiceResult login(String username, String password);
}
