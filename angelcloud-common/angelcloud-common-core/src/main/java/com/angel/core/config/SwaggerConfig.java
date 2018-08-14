package com.angel.core.config;

import com.angel.config.properties.AngelcloudProperties;
import com.angel.config.properties.SwaggerProperties;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * Swagger API 配置文件
 * Created by Administrator on 2018/8/14.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * swagger 配置
     */
    @Resource
    private AngelcloudProperties angelcloudProperties;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                // 当前包路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any()).build();

    }


    //构建api文档的详细信息函数
    private ApiInfo apiInfo() {
        SwaggerProperties swaggerProperties = angelcloudProperties.getSwaggerProperties();
        return new ApiInfoBuilder()
                //页面标题
                .title(swaggerProperties.getTitle())
                //创建人
                .contact(new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()))
                //版本号
                .version(swaggerProperties.getVersion())
                //描述
                .description(swaggerProperties.getDescription())
                .build();
    }
}
