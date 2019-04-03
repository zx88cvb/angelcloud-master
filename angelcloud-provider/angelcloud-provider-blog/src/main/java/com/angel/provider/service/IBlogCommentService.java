package com.angel.provider.service;

import com.angel.base.service.ServiceResult;
import com.angel.provider.model.domain.BlogComment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 博客评论Service
 * @Author angel
 * @Date 19-3-23
 */
public interface IBlogCommentService extends IService<BlogComment> {

    /**
     * 根据id删除博客评论
     * @param id 主键id
     * @return Result 个数
     */
    ServiceResult<Integer> deleteBlogCommentById(int id);
}
