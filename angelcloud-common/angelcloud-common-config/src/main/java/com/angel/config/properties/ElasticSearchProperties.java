package com.angel.config.properties;

import lombok.Data;

/**
 * es 配置
 * @Author angel
 * @Date 19-8-5
 */
@Data
public class ElasticSearchProperties {

    /**
     * 主机
     */
    private String host;

    /**
     * 端口
     */
    private int port;
}
