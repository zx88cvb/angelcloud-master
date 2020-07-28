package com.angel.security.annotation;

import com.angel.security.component.ResourceServerAutoConfiguration;
//import com.angel.security.component.SecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * 资源服务注解
 * @Author angel
 * @Date 2020/7/26
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Import({ ResourceServerAutoConfiguration.class, SecurityBeanDefinitionRegistrar.class })
@Import({ ResourceServerAutoConfiguration.class})
public @interface EnableCustomResourceServer {
}
