package com.angel.provider.mapper;

import com.angel.provider.model.domain.BlogComment;
import com.angel.provider.model.dto.BlogCommentDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 分页条件查询
     * @param page page
     * @param blogCommentDto dto条件
     * @return iPage
     */
    IPage<BlogComment> selectBlogCommentConditionPage(Page page, @Param("condition") BlogCommentDto blogCommentDto);

    /**
     * 根据pId查询
     * @param pId 父级id
     * @return 集合
     */
    List<BlogComment> selectCommentByPId(Integer pId);
}