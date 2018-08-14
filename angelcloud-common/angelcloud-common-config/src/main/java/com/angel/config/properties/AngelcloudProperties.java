package com.angel.config.properties;

import com.angel.base.constant.GlobalConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/14.
 */
@Data
@ConfigurationProperties(prefix = GlobalConstant.ROOT_PREFIX)
@Component
public class AngelcloudProperties {
    private SwaggerProperties swaggerProperties = new SwaggerProperties();
}
