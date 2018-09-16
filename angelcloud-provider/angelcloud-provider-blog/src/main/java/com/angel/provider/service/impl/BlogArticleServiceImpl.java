package com.angel.provider.service.impl;

import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.BlogArticleMapper;
import com.angel.provider.model.domain.BlogArticle;
import com.angel.provider.model.dto.BlogArticleDto;
import com.angel.provider.model.vo.BlogArticleVo;
import com.angel.provider.service.IBlogArticleService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 博客文章表 服务实现类
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements IBlogArticleService {

    @Resource
    private BlogArticleMapper blogArticleMapper;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ServiceResult<Page<BlogArticleVo>> getBlogArticlePage(BlogArticleDto blogArticleDto) {
        return null;
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
        Integer result = blogArticleMapper.insert(blogArticle);
        return ServiceResult.of(result);
    }
}
