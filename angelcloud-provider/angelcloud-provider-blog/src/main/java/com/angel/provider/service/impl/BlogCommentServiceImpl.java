package com.angel.provider.service.impl;

import com.angel.provider.mapper.BlogCommentMapper;
import com.angel.provider.model.domain.BlogComment;
import com.angel.provider.service.IBlogCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 博客评论实现层
 * @Author angel
 * @Date 19-3-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment>
        implements IBlogCommentService {

    @Resource
    private BlogCommentMapper blogCommentMapper;
}
