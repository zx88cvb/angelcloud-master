package com.angel.provider.security.config;

import com.angel.provider.security.handler.AuthenticationFailureEventHandler;
import com.angel.provider.security.handler.AuthenticationSuccessEventHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security 安全配置
 * @Author angel
 * @Date 19-11-12
 */
@Configuration
@Primary
@Order(90)
@AllArgsConstructor
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationSuccessEventHandler authenticationSuccessEventHandler;

    private final AuthenticationFailureEventHandler authenticationFailureEventHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/token/login")
                .loginProcessingUrl("/token/form")
                .successHandler(authenticationSuccessEventHandler)
                .failureHandler(authenticationFailureEventHandler)
                .and().logout()
                .logoutSuccessHandler((request, response, authentication) -> {
                    String referer = request.getHeader(HttpHeaders.REFERER);
                    response.sendRedirect(referer);
                }).deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/token/**",
                        "/actuator/**",
                        "/mobile/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //替换成自己验证规则
        auth.userDetailsService(iLinkUserDetailsService).passwordEncoder(passwordEncoder());
//        auth.authenticationProvider(authenticationProvider());
    }*/


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 失败处理器
     * @return AuthenticationFailureHandler
     *//*
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureEventHandler();
    }

    *//**
     * 成功处理器
     * @return AuthenticationSuccessHandler
     *//*
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessEventHandler();
    }*/


    /*@Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(iLinkUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }*/
}
