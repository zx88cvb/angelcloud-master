package com.angel.security.service.impl;

import com.angel.base.constant.CacheConstants;
import com.angel.base.constant.GlobalConstant;
import com.angel.base.constant.SecurityConstants;
import com.angel.base.constant.ServerResponse;
import com.angel.provider.model.dto.SysUserDto;
import com.angel.provider.model.dto.UserInfo;
import com.angel.provider.service.UserSysUserFeignApi;
import com.angel.security.exception.UserException;
import com.angel.security.model.domain.LinkUser;
import com.angel.security.service.ILinkUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author angel
 * @Date 19-11-12
 */
@Component
@Slf4j
@AllArgsConstructor
public class LinkUserDetailsServiceImpl implements ILinkUserDetailsService {

    private final UserSysUserFeignApi userSysUserFeignApi;
    private final CacheManager cacheManager;

    @Override
    @SneakyThrows
    public UserDetails loadUserBySocial(String code) {
        return null;
    }

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return (LinkUser) cache.get(username).get();
        }

        ServerResponse<UserInfo> result = userSysUserFeignApi.info(username, SecurityConstants.FROM_IN);
        UserDetails userDetails = getUserDetails(result);
        cache.put(username, userDetails);
        return userDetails;
    }

    /**
     * 构建userdetails
     *
     * @param result 用户信息
     * @return
     */
    private UserDetails getUserDetails(ServerResponse<UserInfo> result) {
        if (result == null || result.getData() == null) {
            throw new UserException("用户不存在");
        }

        UserInfo info = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        if (info.getRoles() != null && info.getRoles().length != 0) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

        }
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUserDto user = info.getSysUserDto();
        boolean enabled = user.getStatus() == GlobalConstant.Sys.STATUS_NORMAL;
        // 构造security用户

        return new LinkUser(user.getId(), user.getUsername(), SecurityConstants.BCRYPT + user.getPassword(), enabled,
                true, true, !GlobalConstant.Sys.STATUS_LOCK.equals(user.getStatus()), authorities);
    }
}
