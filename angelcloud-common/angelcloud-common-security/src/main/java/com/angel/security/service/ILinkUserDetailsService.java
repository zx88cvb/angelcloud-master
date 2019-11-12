package com.angel.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Security loadUserByUsername 重写
 * @Author angel
 * @Date 19-11-12
 */
public interface ILinkUserDetailsService extends UserDetailsService {
    /**
     * 根据社交登录code 登录
     *
     * @param code TYPE@CODE
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserBySocial(String code) throws UsernameNotFoundException;
}
