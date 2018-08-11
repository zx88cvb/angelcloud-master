package com.angel.provider.mapper;

import com.angel.provider.model.domain.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统用户表(后台管理) Mapper 接口
 * </p>
 *
 * @author aa
 * @since 2018-07-30
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 通过用户名密码查询用户
     * @param username
     * @param password
     * @return 用户结果集
     */
    SysUser selectByUsernameAndPass(@Param("username") String username,@Param("password") String password);
}
