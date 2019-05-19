package com.angel.provider.service.impl;

import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.SysUserMapper;
import com.angel.provider.model.domain.SysUser;
import com.angel.provider.model.vo.SysUserVo;
import com.angel.provider.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
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
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 返回结果集
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult login(String username, String password) {
        // String passwordNew = DigestUtils.md5Hex(password);
        SysUser sysUser = sysUserMapper.selectByUsernameAndPass(username, password);
        if (sysUser == null) {
            return ServiceResult.notFound();
        }
        SysUserVo sysUserVo = new SysUserVo();
        BeanUtils.copyProperties(sysUser, sysUserVo);
        return ServiceResult.of(sysUserVo);
    }

    /**
     * 根据id查询用户
     * @param userId 用户id
     * @return 返回用户Vo结果集
     */
    @Override
    public ServiceResult<SysUserVo> getUserVoById(Integer userId) {
        // 查询用户
        SysUser sysUser = sysUserMapper.selectById(userId);

        if (sysUser == null) {
            return ServiceResult.notFound();
        }

        SysUserVo sysUserVo = new SysUserVo();
        BeanUtils.copyProperties(sysUser, sysUserVo);

        return ServiceResult.of(sysUserVo);
    }
}
