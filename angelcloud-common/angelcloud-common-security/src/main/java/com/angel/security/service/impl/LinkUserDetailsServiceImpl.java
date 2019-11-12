package com.angel.security.service.impl;

import com.angel.security.service.ILinkUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author angel
 * @Date 19-11-12
 */
@Component
@Slf4j
public class LinkUserDetailsServiceImpl implements ILinkUserDetailsService {
    @Override
    public UserDetails loadUserBySocial(String code) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
