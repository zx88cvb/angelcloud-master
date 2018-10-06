package com.angel.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 七牛云配置
 * Author: Angel
 * Date: 2018/9/24.
 */
@Data
//@ConfigurationProperties(prefix = "angelcloud.qiniu")
public class QiNiuProperties {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String cdnPrefix;
}
