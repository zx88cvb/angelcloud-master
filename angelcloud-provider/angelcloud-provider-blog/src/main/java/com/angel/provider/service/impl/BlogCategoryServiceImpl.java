package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.BlogCategoryMapper;
import com.angel.provider.model.domain.BlogCategory;
import com.angel.provider.model.dto.BlogCategoryDto;
import com.angel.provider.model.form.BlogCategoryForm;
import com.angel.provider.model.vo.BlogCategoryVo;
import com.angel.provider.service.IBlogCategoryService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 博客分类表 服务实现类
 * </p>
 *
 * @author Angel
 * @since 2018-08-13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements IBlogCategoryService {

    @Resource
    private BlogCategoryMapper blogCategoryMapper;

    /**
     * 分页条件查询博客类别
     * @param blogCategory 条件实体类
     * @return 类别集合
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<BlogCategoryVo>> getBlogCategoryPage(BlogCategory blogCategory) {
        Page<BlogCategoryVo> page = new Page<>(blogCategory.getPageNum(), blogCategory.getPageSize());

        //条件查询
        Wrapper<BlogCategory> entity = new EntityWrapper<>();
        entity.eq("is_del", GlobalConstant.IsDel.NO)
                .like("category_name", blogCategory.getCategoryName())
        .orderDesc(Lists.newArrayList("create_time"));
        List<BlogCategory> blogCategoryList = blogCategoryMapper.selectPage(page, entity);

        //将BlogCategory 转换成Vo对象
        List<BlogCategoryVo> collect = blogCategoryList.stream().map(e -> {
            BlogCategoryVo blogCategoryVo = new BlogCategoryVo();
            BeanUtils.copyProperties(e, blogCategoryVo);
            return blogCategoryVo;
        }).collect(Collectors.toList());

        page.setRecords(collect);

        return ServiceResult.of(page);
    }

    /**
     * 新增博客分类
     * @param blogCategoryDto 博客分类DTO
     * @return 插入成功个数
     */
    @Override
    public ServiceResult<Integer> insertBlogCategory(BlogCategoryDto blogCategoryDto) {
        BlogCategory blogCategory = new BlogCategory();
        BeanUtils.copyProperties(blogCategoryDto, blogCategory);
        //返回个数
        Integer count = blogCategoryMapper.insert(blogCategory);
        return ServiceResult.of(count);
    }

    /**
     * 修改博客分类
     * @param blogCategoryDto 博客分类DTO
     * @return 修改成功个数
     */
    @Override
    public ServiceResult<Integer> updateBlogCategory(BlogCategoryDto blogCategoryDto) {
        BlogCategory blogCategory = new BlogCategory();
        BeanUtils.copyProperties(blogCategoryDto, blogCategory);
        blogCategory.setUpdateTime(new Date());
        //返回个数
        Integer count = blogCategoryMapper.updateById(blogCategory);
        return ServiceResult.of(count);
    }

    /**
     * 根据id删除博客 (逻辑删除)
     * @param id 主键id
     * @return 删除个数
     */
    @Override
    public ServiceResult<Integer> deleteBlogCategoryById(Integer id) {
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setId(id);
        blogCategory.setIsDel(GlobalConstant.IsDel.YES);
        blogCategory.setUpdateTime(new Date());
        Integer count = blogCategoryMapper.updateById(blogCategory);
        return ServiceResult.of(count);
    }
}
