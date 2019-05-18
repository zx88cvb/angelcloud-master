package com.angel.config;

import com.angel.config.properties.AngelcloudProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 开启加载配置
 * @Author angel
 * @Date 19-5-15
 */
@Configuration
@EnableConfigurationProperties(AngelcloudProperties.class)
public class AngelcloudCoreConfig {
}
