package com.angel.security.component;

import com.angel.base.constant.SecurityConstants;
import com.angel.security.model.domain.LinkUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * token增强，客户端模式不增强。
 * @Author angel
 * @Date 2019/12/28
 */
@Component
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        if (SecurityConstants.CLIENT_CREDENTIALS
                .equals(oAuth2Authentication.getOAuth2Request().getGrantType())) {
            return oAuth2AccessToken;
        }

        final Map<String, Object> additionalInfo = new HashMap<>(8);
        LinkUser linkUser = (LinkUser) oAuth2Authentication.getUserAuthentication().getPrincipal();
        additionalInfo.put(SecurityConstants.DETAILS_USER_ID, linkUser.getId());
        additionalInfo.put(SecurityConstants.DETAILS_USERNAME, linkUser.getUsername());
        additionalInfo.put(SecurityConstants.DETAILS_LICENSE, SecurityConstants.ANGELCLOUD_LICENSE);
        additionalInfo.put(SecurityConstants.ACTIVE, Boolean.TRUE);
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }
}
