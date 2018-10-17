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
    // Swagger文档工具
    private SwaggerProperties swaggerProperties = new SwaggerProperties();

    // 七牛云
    private QiNiuProperties qiniu = new QiNiuProperties();

    // 线程池
    private AsyncTaskProperties task = new AsyncTaskProperties();
}
