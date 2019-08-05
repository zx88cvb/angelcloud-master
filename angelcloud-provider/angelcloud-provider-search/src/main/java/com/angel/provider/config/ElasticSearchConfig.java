package com.angel.provider.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticSearch 配置文件
 * @Author angel
 * @Date 19-8-4
 */
@Configuration
public class ElasticSearchConfig {
    public RestClient esClient() {
        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
        return clientBuilder.build();
    }
}