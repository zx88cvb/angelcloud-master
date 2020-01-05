package com.angel.provider.service.impl;

import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.SysMenuMapper;
import com.angel.provider.mapper.SysRoleMapper;
import com.angel.provider.mapper.SysUserMapper;
import com.angel.provider.model.domain.SysRole;
import com.angel.provider.model.domain.SysUser;
import com.angel.provider.model.dto.SysUserDto;
import com.angel.provider.model.dto.UserInfo;
import com.angel.provider.model.vo.SysMenuVo;
import com.angel.provider.model.vo.SysUserVo;
import com.angel.provider.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
@AllArgsConstructor
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final SysUserMapper sysUserMapper;

    private final SysRoleMapper sysRoleMapper;

    private final SysMenuMapper sysMenuMapper;

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

    @Override
    public ServiceResult<UserInfo> findUserInfo(SysUser sysUser) {
        UserInfo userInfo = new UserInfo();
        SysUserDto sysUserDto = new SysUserDto();
        BeanUtils.copyProperties(sysUser, sysUserDto);

        // userInfo set userDto
        userInfo.setSysUserDto(sysUserDto);
        //设置角色列表  （ID）
        List<Integer> roleIds = sysRoleMapper.findRolesByUserId(sysUser.getId())
                .stream()
                .map(SysRole::getId)
                .collect(Collectors.toList());
//        (Integer[]) roleIds.toArray()  java.lang.ClassCastException
        userInfo.setRoles(roleIds.toArray(new Integer[0]));

        //设置权限列表（menu.permission）
        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> permissionList = sysMenuMapper.findMenuByRoleId(roleId)
                    .stream()
                    .filter(menuVo -> StringUtils.isNotEmpty(menuVo.getPermission()))
                    .map(SysMenuVo::getPermission)
                    .collect(Collectors.toList());
            permissions.addAll(permissionList);
        });
        userInfo.setPermissions(permissions.toArray(new String[0]));
        return ServiceResult.of(userInfo);
    }
}
