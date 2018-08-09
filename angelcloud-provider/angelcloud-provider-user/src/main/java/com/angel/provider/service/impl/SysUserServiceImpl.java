package com.angel.provider.service.impl;

import com.angel.provider.mapper.SysUserMapper;
import com.angel.provider.model.domain.SysUser;
import com.angel.provider.service.ISysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 系统用户表(后台管理) 服务实现类
 * </p>
 *
 * @author aa
 * @since 2018-07-30
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
}
