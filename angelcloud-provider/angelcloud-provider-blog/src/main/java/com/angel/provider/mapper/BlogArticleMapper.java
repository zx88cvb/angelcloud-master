package com.angel.provider.mapper;

import com.angel.provider.model.domain.BlogArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 博客文章表 Mapper 接口
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {

    IPage<BlogArticle> selectBlogArticleConditionPage(Page page, @Param("blogArticle") BlogArticle blogArticle);
}
