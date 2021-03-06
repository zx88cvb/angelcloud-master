package com.angel.provider.service;

import com.angel.base.constant.ServerResponse;
import com.angel.provider.model.vo.BlogArticleVo;
import com.angel.provider.service.hystrix.BlogArticleFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    /**
     * 根据评论多少查询
     * @param count 查询个数
     * @return 集合
     */
    @GetMapping("/api/blog/article/comment/{count}")
    ServerResponse<List<BlogArticleVo>> selectCommentTop(@PathVariable("count") Integer count);

    /**
     * 组合查询评论,随机文章,标签云
     * @param count 查询个数
     * @return map
     */
    @GetMapping("/api/blog/article/three/comment/{count}")
    ServerResponse selectCommentTopAndRandThree(@PathVariable("count") Integer count);
}
