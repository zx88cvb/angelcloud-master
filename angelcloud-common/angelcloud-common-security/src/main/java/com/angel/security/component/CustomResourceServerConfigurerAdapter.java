package com.angel.security.component;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;

/**
 * 支持remoteTokenServices 负载均衡 2. 支持 获取用户全部信息
 * @Author angel
 * @Date 2020/7/25
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class CustomResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

    private final ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

    private final ResourceServerProperties resourceServerProperties;

    /*@Autowired
    private RemoteTokenServices remoteTokenServices;*/

    private final PermitAllUrlProperties permitAllUrlProperties;

    private final TokenExtractor tokenExtractor;

    private final RestTemplate lbRestTemplate;

    private final CustomUserAuthenticationConverter customUserAuthenticationConverter;

    /**
     * 默认的配置，对外暴露
     * @param httpSecurity
     */
    @Override
    @SneakyThrows
    public void configure(HttpSecurity httpSecurity) {
        // 允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        httpSecurity.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();
        permitAllUrlProperties.getIgnoreUrls().forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated().and().csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);

        CustomCheckTokenServices remoteTokenServices = new CustomCheckTokenServices(lbRestTemplate);
//        remoteTokenServices.setRestTemplate(lbRestTemplate);
//        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        remoteTokenServices.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
        remoteTokenServices.setClientId(resourceServerProperties.getClientId());
        remoteTokenServices.setClientSecret(resourceServerProperties.getClientSecret());
        /*resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
                .tokenExtractor(tokenExtractor)
                .tokenServices(remoteTokenServices);*/
        resources.tokenServices(remoteTokenServices);
        /*resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint).tokenExtractor(tokenExtractor)
                .tokenServices(remoteTokenServices);*/
    }
}
