package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.BlogArticle;
import com.angel.provider.model.dto.BlogArticleDto;
import com.angel.provider.model.vo.BlogArticleVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 博客文章表 服务类
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
public interface IBlogArticleService extends IService<BlogArticle> {
    /**
     * 分页条件查询博客内容
     * @param blogArticleDto 条件实体类
     * @return 类别集合
     */
    ServiceResult<Page<BlogArticleDto>> getBlogArticlePage (BlogArticleDto blogArticleDto);

    /**
     * 新增博客文章
     * @param blogArticleDto 条件实体类DTO
     * @return 返回新增个数
     */
    ServiceResult<Integer> insertBlogArticle (BlogArticleDto blogArticleDto);

    /**
     * 根据id查询文章
     * @param id 文章id
     * @return 文章结果集
     */
    ServiceResult<BlogArticleDto> getBlogArticleById (Integer id);

    /**
     * 修改博客文章
     * @param blogArticleDto 条件实体类DTO
     * @return 返回修改个数
     */
    ServiceResult<Integer> updateBlogArticle (BlogArticleDto blogArticleDto);

    /**
     * 删除博客文章
     * @param id 主键id
     * @return 返回删除个数结果集
     */
    ServiceResult<Integer> deleteBlogArticleById(int id);
}
