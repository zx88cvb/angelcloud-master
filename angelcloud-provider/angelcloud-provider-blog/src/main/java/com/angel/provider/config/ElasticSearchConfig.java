package com.angel.provider.config;

import com.angel.config.properties.AngelcloudProperties;
import com.angel.config.properties.ElasticSearchProperties;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * ElasticSearch 配置文件
 * @Author angel
 * @Date 19-8-4
 */
@Configuration
public class ElasticSearchConfig {

    @Resource
    private AngelcloudProperties angelcloudProperties;

    public RestHighLevelClient esClient() {
        // 获取es配置项
        ElasticSearchProperties es = angelcloudProperties.getEs();

        RestHighLevelClient clientBuilder = new RestHighLevelClient
                (RestClient.builder(new HttpHost(es.getHost(), es.getPort(), "http")));
        return clientBuilder;
    }
}