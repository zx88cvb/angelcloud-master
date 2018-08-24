package com.angel.provider.service.impl;

import com.angel.provider.mapper.BlogArticleMapper;
import com.angel.provider.model.domain.BlogArticle;
import com.angel.provider.service.IBlogArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
