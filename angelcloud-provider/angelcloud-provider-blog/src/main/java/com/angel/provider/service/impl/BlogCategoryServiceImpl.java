package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.BlogCategoryMapper;
import com.angel.provider.model.domain.BlogCategory;
import com.angel.provider.model.dto.BlogCategoryDto;
import com.angel.provider.model.vo.BlogCategoryVo;
import com.angel.provider.service.IBlogCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        Page<BlogCategoryVo> page = new Page<BlogCategoryVo>();
        //条件查询
        LambdaQueryWrapper<BlogCategory> entity = new QueryWrapper<BlogCategory>().lambda()
                .eq(BlogCategory:: getIsDel, GlobalConstant.IsDel.NO)
                .like(BlogCategory:: getCategoryName, blogCategory.getCategoryName() == null ? "" : blogCategory.getCategoryName())
                .orderByDesc(BlogCategory:: getCreateTime);

        // List<BlogCategory> blogCategoryList = blogCategoryMapper.selectPage(page, entity);
        IPage<BlogCategory> iPageBlogCategory = blogCategoryMapper.selectPage(new Page<>(blogCategory.getPageNum(), blogCategory.getPageSize()), entity);

        // 获取集合对象
        List<BlogCategory> blogCategoryList = iPageBlogCategory.getRecords();

        //将BlogCategory 转换成Vo对象
        List<BlogCategoryVo> collect = blogCategoryList.stream().map(e -> {
            BlogCategoryVo blogCategoryVo = new BlogCategoryVo();
            BeanUtils.copyProperties(e, blogCategoryVo);
            return blogCategoryVo;
        }).collect(Collectors.toList());

        BeanUtils.copyProperties(iPageBlogCategory, page);
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
        if (count < 1) {
            return ServiceResult.notFound();
        }
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
        if (count < 1) {
            return ServiceResult.notFound();
        }
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
        if (count < 1) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }


    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<List<BlogCategoryVo>> getBlogCategoryAll(BlogCategory blogCategory) {
        //条件查询
        LambdaQueryWrapper<BlogCategory> entity = new QueryWrapper<BlogCategory>().lambda()
                .eq(BlogCategory:: getIsDel, GlobalConstant.IsDel.NO)
                .like(BlogCategory:: getCategoryName, blogCategory.getCategoryName() == null ? "" : blogCategory.getCategoryName())
                .orderByDesc(BlogCategory:: getCreateTime);
        List<BlogCategory> blogCategoryList = blogCategoryMapper.selectList(entity);
        //将BlogCategory 转换成Vo对象
        List<BlogCategoryVo> collect = blogCategoryList.stream().map(e -> {
            BlogCategoryVo blogCategoryVo = new BlogCategoryVo();
            BeanUtils.copyProperties(e, blogCategoryVo);
            return blogCategoryVo;
        }).collect(Collectors.toList());

        return ServiceResult.of(collect);
    }
}
