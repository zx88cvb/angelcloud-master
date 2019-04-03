package com.angel.provider.service.impl;

import com.angel.base.constant.GlobalConstant;
import com.angel.base.service.ServiceResult;
import com.angel.provider.mapper.BlogCommentMapper;
import com.angel.provider.model.domain.BlogComment;
import com.angel.provider.service.IBlogCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

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

    @Override
    public ServiceResult<Integer> deleteBlogCommentById(int id) {
        BlogComment blogComment = new BlogComment();
        blogComment.setId(id);
        blogComment.setIsDel(GlobalConstant.IsDel.YES);
        blogComment.setUpdateTime(new Date());
        Integer count = blogCommentMapper.updateById(blogComment);
        if (count < 1) {
            return ServiceResult.notFound();
        }
        return ServiceResult.of(count);
    }
}
