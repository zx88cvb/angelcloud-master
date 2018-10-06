package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.BlogCategory;
import com.angel.provider.model.dto.BlogCategoryDto;
import com.angel.provider.model.form.BlogCategoryForm;
import com.angel.provider.model.vo.BlogCategoryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博客分类表 服务类
 * </p>
 *
 * @author Angel
 * @since 2018-08-13
 */
public interface IBlogCategoryService extends IService<BlogCategory> {

    /**
     * 分页条件查询博客类别
     * @param blogCategory 条件实体类
     * @return 类别集合
     */
    ServiceResult<Page<BlogCategoryVo>> getBlogCategoryPage (BlogCategory blogCategory);

    /**
     * 新增博客分类
     * @param blogCategoryDto 博客分类DTO
     * @return 插入成功个数
     */
    ServiceResult<Integer> insertBlogCategory(BlogCategoryDto blogCategoryDto);

    /**
     * 修改博客分类
     * @param blogCategoryDto 博客分类DTO
     * @return 修改成功个数
     */
    ServiceResult<Integer> updateBlogCategory(BlogCategoryDto blogCategoryDto);

    /**
     * 根据id删除博客 (逻辑删除)
     * @param id 主键id
     * @return 删除个数
     */
    ServiceResult<Integer> deleteBlogCategoryById (Integer id);

    /**
     * 条件查询所有博客类别
     * @param blogCategory 条件实体类
     * @return 类别集合
     */
    ServiceResult<List<BlogCategoryVo>> getBlogCategoryAll(BlogCategory blogCategory);
}
