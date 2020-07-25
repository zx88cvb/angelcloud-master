package com.angel.security.component;

import cn.hutool.core.map.MapUtil;
import com.angel.base.constant.SecurityConstants;
import com.angel.security.model.domain.LinkUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 根据checktoken 的结果转化用户信息
 * @Author angel
 * @Date 2020/7/25
 */
@Slf4j
@Component
public class CustomUserAuthenticationConverter implements UserAuthenticationConverter {

    private static final String N_A = "N/A";

    /**
     * Extract information about the user to be used in an access token (i.e. for resource
     * servers).
     * @param authentication an authentication representing a user
     * @return a map of key values representing the unique information about the user
     */
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(USERNAME, authentication.getName());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }

    /**
     * Inverse of {@link #convertUserAuthentication(Authentication)}. Extracts an
     * Authentication from a map.
     * @param responseMap a map of user information
     * @return an Authentication representing the user or null if there is none
     */
    @Override
    public Authentication extractAuthentication(Map<String, ?> responseMap) {
        if (responseMap.containsKey(USERNAME)) {
            Collection<? extends GrantedAuthority> authorities = getAuthorities(responseMap);
            Map<String, ?> map = MapUtil.get(responseMap, SecurityConstants.DETAILS_USER, Map.class);
            String username = MapUtil.getStr(map, SecurityConstants.DETAILS_USERNAME);
            Integer id = MapUtil.getInt(map, SecurityConstants.DETAILS_USER_ID);
//            Integer deptId = MapUtil.getInt(map, SecurityConstants.DETAILS_DEPT_ID);
            /*String phone = MapUtil.getStr(map, SecurityConstants.DETAILS_PHONE);
            String avatar = MapUtil.getStr(map, SecurityConstants.DETAILS_AVATAR);*/
            LinkUser user = new LinkUser(id, username, N_A, true, true, true, true,
                    authorities);
            return new UsernamePasswordAuthenticationToken(user, N_A, authorities);
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(
                    StringUtils.collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        return AuthorityUtils.NO_AUTHORITIES;
    }

}
