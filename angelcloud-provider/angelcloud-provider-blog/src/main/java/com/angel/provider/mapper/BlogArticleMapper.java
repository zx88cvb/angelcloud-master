package com.angel.provider.mapper;

import com.angel.provider.model.domain.BlogArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 分页条件查询文章
     * @param page page
     * @param blogArticle 实体
     * @return iPage对象
     */
    IPage<BlogArticle> selectBlogArticleConditionPage(Page page, @Param("blogArticle") BlogArticle blogArticle);

    /**
     * 新增文章
     * @param blogArticle 文章实体类
     * @return 返回id
     */
    int insertForId(BlogArticle blogArticle);

    /**
     * 根据id查询
     * @param id 主键id
     * @return 单个实体
     */
    BlogArticle selectByPrimaryId(Integer id);

    /**
     * 随机查询3条文章
     * @return 集合
     */
    List<BlogArticle> selectRandArticleThree();
}
