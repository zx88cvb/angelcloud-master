package com.angel.provider.mapper;

import com.angel.provider.model.domain.BlogArticle;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 博客文章表 Mapper 接口
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {

    // List<BlogArticle> selectBlogArticleConditionPage(BlogArticle blogArticle);
}
