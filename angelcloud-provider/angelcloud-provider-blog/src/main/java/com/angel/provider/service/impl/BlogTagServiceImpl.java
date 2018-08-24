package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.BlogTagMapper;
import com.angel.provider.model.domain.BlogTag;
import com.angel.provider.model.dto.BlogTagDto;
import com.angel.provider.model.form.BlogTagForm;
import com.angel.provider.model.vo.BlogTagVo;
import com.angel.provider.service.IBlogTagService;
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
 * 博客标签表 服务实现类
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements IBlogTagService {

    @Resource
    private BlogTagMapper blogTagMapper;

    /**
     * 分页条件查询博客标签
     * @param blogTag 条件实体类
     * @return 类别集合
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<BlogTagVo>> getBlogTagPage(BlogTag blogTag) {
        Page<BlogTagVo> page = new Page<>(blogTag.getPageNum(), blogTag.getPageSize());

        //条件查询
        Wrapper<BlogTag> entity = new EntityWrapper<>();
        entity.eq("is_del", GlobalConstant.IsDel.NO)
                .like("tag_name", blogTag.getTagName())
                .orderDesc(Lists.newArrayList("create_time"));
        List<BlogTag> blogTagList = blogTagMapper.selectPage(page, entity);

        //将BlogTag 转换成Vo对象
        List<BlogTagVo> collect = blogTagList.stream().map(e -> {
            BlogTagVo blogTagVo = new BlogTagVo();
            BeanUtils.copyProperties(e, blogTagVo);
            return blogTagVo;
        }).collect(Collectors.toList());

        page.setRecords(collect);

        return ServiceResult.of(page);
    }

    /**
     * 新增博客标签
     * @param blogTagDto 博客分类DTO
     * @return 插入成功个数
     */
    @Override
    public ServiceResult<Integer> insertBlogTag(BlogTagDto blogTagDto) {
        BlogTag blogTag = new BlogTag();
        BeanUtils.copyProperties(blogTagDto, blogTag);
        //返回个数
        Integer count = blogTagMapper.insert(blogTag);
        return ServiceResult.of(count);
    }

    /**
     * 修改博客标签
     * @param blogTagDto 博客分类DTO
     * @return 修改成功个数
     */
    @Override
    public ServiceResult<Integer> updateBlogTag(BlogTagDto blogTagDto) {
        BlogTag blogTag = new BlogTag();
        BeanUtils.copyProperties(blogTagDto, blogTag);
        blogTag.setUpdateTime(new Date());
        //返回个数
        Integer count = blogTagMapper.updateById(blogTag);
        return ServiceResult.of(count);
    }

    /**
     * 根据id删除博客标签 (逻辑删除)
     * @param id 主键id
     * @return 删除个数
     */
    @Override
    public ServiceResult<Integer> deleteBlogTagById(Integer id) {
        BlogTag blogTag = new BlogTag();
        blogTag.setId(id);
        blogTag.setIsDel(GlobalConstant.IsDel.YES);
        Integer count = blogTagMapper.updateById(blogTag);
        return ServiceResult.of(count);
    }
}
