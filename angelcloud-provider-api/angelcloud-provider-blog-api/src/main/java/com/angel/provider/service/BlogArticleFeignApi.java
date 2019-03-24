package com.angel.provider.service;

import com.angel.base.constant.ServerResponse;
import com.angel.provider.model.vo.BlogArticleVo;
import com.angel.provider.service.hystrix.BlogArticleFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 博客文章feign API
 * @Author angel
 * @Date 19-3-24
 */
@FeignClient(name = "angelcloud-provider-blog",fallback = BlogArticleFeignHystrix.class)
public interface BlogArticleFeignApi {

    /**
     * 随机查询3篇文章
     * @return
     */
    @GetMapping("/api/blog/article/rand")
    ServerResponse<List<BlogArticleVo>> selectRandArticleThree();
}
