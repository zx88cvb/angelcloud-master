package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.BlogTag;
import com.angel.provider.model.dto.BlogTagDto;
import com.angel.provider.model.form.BlogTagForm;
import com.angel.provider.model.vo.BlogTagVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博客标签表 服务类
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
public interface IBlogTagService extends IService<BlogTag> {

    /**
     * 分页条件查询博客标签
     * @param blogTag 条件实体类
     * @return 类别集合
     */
    ServiceResult<Page<BlogTagVo>> getBlogTagPage (BlogTag blogTag);

    /**
     * 新增博客标签
     * @param blogTagDto 博客分类DTO
     * @return 插入成功个数
     */
    ServiceResult<Integer> insertBlogTag(BlogTagDto blogTagDto);

    /**
     * 修改博客标签
     * @param blogTagDto 博客分类DTO
     * @return 修改成功个数
     */
    ServiceResult<Integer> updateBlogTag(BlogTagDto blogTagDto);

    /**
     * 根据id删除博客标签 (逻辑删除)
     * @param id 主键id
     * @return 删除个数
     */
    ServiceResult<Integer> deleteBlogTagById (Integer id);

    /**
     * 查询全部标签
     * @return 集合对象
     */
    ServiceResult<List<BlogTagVo>> getAllTag();
}
