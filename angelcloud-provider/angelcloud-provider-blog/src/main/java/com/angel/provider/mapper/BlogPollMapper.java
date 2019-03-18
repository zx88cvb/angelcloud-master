package com.angel.provider.mapper;

import com.angel.provider.model.domain.BlogPoll;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 文章点赞表Mapper
 * @Author: Angel
 * @Date: 2019/03/18.
 */
public interface BlogPollMapper extends BaseMapper<BlogPoll> {

    /**
     * 根据文章id查询点赞次数
     * @param articleId 文章id
     * @return 总个数
     */
    Long selectCountByArticleId(Integer articleId);
}