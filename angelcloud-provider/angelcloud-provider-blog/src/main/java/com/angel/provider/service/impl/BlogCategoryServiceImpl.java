package com.angel.provider.service.impl;

import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.BlogCategoryMapper;
import com.angel.provider.model.domain.BlogCategory;
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

    @Override
    public ServiceResult<Page<BlogCategoryVo>> getBlogCategoryPage(BlogCategory blogCategory) {
        Page<BlogCategoryVo> page = new Page<>(blogCategory.getPageNum(), blogCategory.getPageSize());

        //条件查询
        Wrapper<BlogCategory> entity = new EntityWrapper<>();
        entity.like("category_name", blogCategory.getCategoryName())
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
}
