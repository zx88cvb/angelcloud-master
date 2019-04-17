package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.service.ServiceResult;
import com.angel.provider.exceptions.BlogBizException;
import com.angel.provider.mapper.*;
import com.angel.provider.model.domain.BlogArticle;
import com.angel.provider.model.domain.BlogArticleTag;
import com.angel.provider.model.domain.BlogTag;
import com.angel.provider.model.dto.BlogArticleDto;
import com.angel.provider.model.dto.BlogCategoryDto;
import com.angel.provider.model.vo.*;
import com.angel.provider.service.IBlogArticleService;
import com.angel.provider.service.IUserSysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 博客文章表 服务实现类
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements IBlogArticleService {

    @Resource
    private BlogArticleMapper blogArticleMapper;

    @Resource
    private BlogArticleTagMapper blogArticleTagMapper;

    @Resource
    private BlogTagMapper blogTagMapper;

    @Resource
    private IUserSysUserService iUserSysUserService;

    @Resource
    private BlogPollMapper blogPollMapper;

    @Resource
    private BlogCommentMapper blogCommentMapper;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<BlogArticleVo>> getBlogArticlePage(BlogArticleDto blogArticleDto) {
        // 将当前页和每页显示数量加入Page对象
        Page<BlogArticleVo> page = new Page<>();

        //查询
        IPage<BlogArticle> blogArticlePage = blogArticleMapper
                .selectBlogArticleConditionPage(new Page<>(blogArticleDto.getPageNum(), blogArticleDto.getPageSize()),
                        blogArticleDto);

        List<BlogArticle> blogArticleList = blogArticlePage.getRecords();

        // stram() 转换成 VO LIST
        List<BlogArticleVo> articleVoList = blogArticleList.stream().map(e -> {
            BlogArticleVo blogArticleItem = new BlogArticleVo();
            BeanUtils.copyProperties(e, blogArticleItem);

            // 查询喜欢（点赞）个数
            long count = blogPollMapper.selectCountByArticleId(e.getId());
            blogArticleItem.setPollCount(count);

            // Category -> CategoryVo
            BlogCategoryVo blogCategoryVoItem = new BlogCategoryVo();
            BeanUtils.copyProperties(e.getBlogCategory(), blogCategoryVoItem);
            blogArticleItem.setBlogCategoryVo(blogCategoryVoItem);

            // feign 远程调用 获取SysUserVo
            ServiceResult<SysUserVo> userVo = iUserSysUserService.getUserVo(e.getUserId());
            SysUserVo sysUserVo = userVo.getResult();
            blogArticleItem.setSysUserVo(sysUserVo);
            return blogArticleItem;
        }).collect(Collectors.toList());

        BeanUtils.copyProperties(blogArticlePage, page);
        // 将数据添加到Page
        page.setRecords(articleVoList);

        return ServiceResult.of(page);
    }

    /**
     * 新增博客文章
     * @param blogArticleDto 条件实体类DTO
     * @return 返回成功个数
     */
    @Override
    public ServiceResult<Integer> insertBlogArticle(BlogArticleDto blogArticleDto) {
        BlogArticle blogArticle = new BlogArticle();
        BeanUtils.copyProperties(blogArticleDto, blogArticle);

        //新增文章 返回id
        int result = blogArticleMapper.insertForId(blogArticle);

        // 获取tag集合
        List<BlogTagVo> tagList = blogArticleDto.getTagList();

        // 获取tag id集合
        List<Integer> tagIdList = tagList.stream().map(e -> e.getId()).collect(Collectors.toList());

        // 判断是否有标签
        if (!tagList.isEmpty()) {
            int tagResult = blogArticleTagMapper.insertBatch(tagIdList, blogArticle.getId());
            if (tagResult == GlobalConstant.Attribute.NO) {
                throw new BlogBizException(ErrorCodeEnum.BLOG10031009);
            }
        }

        if (result == GlobalConstant.Attribute.NO) {
            throw new BlogBizException(ErrorCodeEnum.BLOG10031002);
        }
        return ServiceResult.of(result);
    }

    /**
     * 根据id查询文章
     * @param id 文章id
     * @return 文章结果集
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<BlogArticleVo> getBlogArticleById(Integer id) {
        // 根据id查询
        BlogArticle blogArticle = blogArticleMapper.selectByPrimaryId(id);

        if (blogArticle == null) {
            return ServiceResult.notFound();
        }

        // 创建vo对象
        BlogArticleVo blogArticleVo = new BlogArticleVo();
        BeanUtils.copyProperties(blogArticle, blogArticleVo);

        // 评论数量
        long commentCount = blogCommentMapper.selectCountByArticleId(id);
        blogArticleVo.setCommentCount(commentCount);

        // 喜欢数量
        long pollCount = blogPollMapper.selectCountByArticleId(id);
        blogArticleVo.setPollCount(pollCount);

        // 设置类型Vo
        BlogCategoryVo blogCategoryVo = new BlogCategoryVo();
        BeanUtils.copyProperties(blogArticle.getBlogCategory(), blogCategoryVo);
        blogArticleVo.setBlogCategoryVo(blogCategoryVo);

        //条件查询 文章标签表
        LambdaQueryWrapper<BlogArticleTag> entity = new QueryWrapper<BlogArticleTag>().lambda()
                .eq(BlogArticleTag:: getArticleId, id);
        List<BlogArticleTag> blogArticleTagList = blogArticleTagMapper.selectList(entity);

        if (!blogArticleTagList.isEmpty()) {
            List<Integer> tagIdList = blogArticleTagList.stream().map(e -> e.getTagId()).collect(Collectors.toList());

            // 根据id集合查询Tag标签
            List<BlogTag> blogTagList = blogTagMapper.selectBatchIds(tagIdList);
            List<BlogTagVo> blogTagVoList = blogTagList.stream().map(e -> {
                BlogTagVo blogTagVo = new BlogTagVo();
                BeanUtils.copyProperties(e, blogTagVo);
                return blogTagVo;
            }).collect(Collectors.toList());
            BeanUtils.copyProperties(blogTagList, blogTagVoList);

            blogArticleVo.setTagList(blogTagVoList);
        }

        return ServiceResult.of(blogArticleVo);
    }

    /**
     * 修改博客文章
     * @param blogArticleDto 条件实体类DTO
     * @return 返回成功个数
     */
    @Override
    public ServiceResult<Integer> updateBlogArticle(BlogArticleDto blogArticleDto) {
        BlogArticle blogArticle = new BlogArticle();
        BeanUtils.copyProperties(blogArticleDto, blogArticle);

        //修改时间
        blogArticle.setUpdateTime(new Date());

        //修改文章
        int result = blogArticleMapper.updateById(blogArticle);

        // 获取tag集合
        List<BlogTagVo> tagList = blogArticleDto.getTagList();

        // 获取tag id集合
        List<Integer> tagIdList = tagList.stream().map(e -> e.getId()).collect(Collectors.toList());

        // 判断是否有标签
        if (!tagList.isEmpty()) {
            //条件 文章标签表
            LambdaQueryWrapper<BlogArticleTag> entity = new QueryWrapper<BlogArticleTag>().lambda()
                    .eq(BlogArticleTag:: getArticleId, blogArticle.getId());
            // 根据文章id先删除所有标签 再创建
            int deleteResult = blogArticleTagMapper.delete(entity);

            if (deleteResult == GlobalConstant.Attribute.NO) {
                throw new BlogBizException(ErrorCodeEnum.BLOG10031011);
            }

            // 创建
            int tagResult = blogArticleTagMapper.insertBatch(tagIdList, blogArticle.getId());

            if (tagResult == GlobalConstant.Attribute.NO) {
                throw new BlogBizException(ErrorCodeEnum.BLOG10031010);
            }
        }

        if (result == GlobalConstant.Attribute.NO) {
            throw new BlogBizException(ErrorCodeEnum.BLOG10031002);
        }
        return ServiceResult.of(result);
    }

    /**
     * 删除博客文章
     * @param id 主键id
     * @return 返回删除个数结果集
     */
    @Override
    public ServiceResult<Integer> deleteBlogArticleById(int id) {
        BlogArticle blogCategory = new BlogArticle();
        blogCategory.setId(id);
        blogCategory.setIsDel(GlobalConstant.IsDel.YES);
        blogCategory.setUpdateTime(new Date());
        Integer count = blogArticleMapper.updateById(blogCategory);
        if (count < GlobalConstant.Attribute.YES) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<List<BlogArticleVo>> selectRandArticleThree() {
        // 查询
        List<BlogArticle> blogArticleList = blogArticleMapper.selectRandArticleThree();
        if (blogArticleList == null) {
            return ServiceResult.notFound();
        }

        // VO转换
        List<BlogArticleVo> blogArticleVoList = blogArticleList.stream().map(e -> {
            BlogArticleVo blogArticleVo = new BlogArticleVo();
            BeanUtils.copyProperties(e, blogArticleVo);
            return blogArticleVo;
        }).collect(Collectors.toList());
        return ServiceResult.of(blogArticleVoList);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<List<BlogArticleVo>> selectCommentTop(Integer count) {
        // 查询
        List<BlogArticle> blogArticleList = blogArticleMapper.selectCommentTop(count);
        if (blogArticleList == null) {
            return ServiceResult.notFound();
        }
        // VO转换
        List<BlogArticleVo> blogArticleVoList = blogArticleList.stream().map(e -> {
            BlogArticleVo blogArticleVo = new BlogArticleVo();
            BeanUtils.copyProperties(e, blogArticleVo);
            return blogArticleVo;
        }).collect(Collectors.toList());
        return ServiceResult.of(blogArticleVoList);
    }
}
