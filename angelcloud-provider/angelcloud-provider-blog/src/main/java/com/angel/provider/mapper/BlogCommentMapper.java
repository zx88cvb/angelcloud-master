package com.angel.provider.mapper;

import com.angel.provider.model.domain.BlogComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 博客评论表 Mapper 接口
 * </p>
 *
 * @author Angel
 * @since 2019-03-23
 */
public interface BlogCommentMapper extends BaseMapper<BlogComment> {
    /**
     * 根据文章id查询评论个数
     * @param articleId 文章id
     * @return 总个数
     */
    long selectCountByArticleId(Integer articleId);
}