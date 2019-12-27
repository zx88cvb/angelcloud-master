package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.SysUser;
import com.angel.provider.model.dto.UserInfo;
import com.angel.provider.model.vo.SysUserVo;
import com.baomidou.mybatisplus.extension.service.IService;

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

    /**
     *
     * @param userId 用户id
     * @return 返回用户Vo结果集
     */
    ServiceResult<SysUserVo> getUserVoById(Integer userId);

    /**
     * 查询用户信息
     *
     * @param user 用户
     * @return userInfo
     */
    ServiceResult<UserInfo> findUserInfo(SysUser user);
}
