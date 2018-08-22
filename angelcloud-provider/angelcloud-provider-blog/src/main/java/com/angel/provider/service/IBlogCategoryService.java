package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.BlogCategory;
import com.angel.provider.model.vo.BlogCategoryVo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

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
}
