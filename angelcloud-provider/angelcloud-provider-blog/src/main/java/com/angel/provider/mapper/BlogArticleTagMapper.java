package com.angel.provider.mapper;

import com.angel.provider.model.domain.BlogArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章表 标签表 关联表Mapper
 * @Author: Angel
 * @Date: 2018/10/11.
 * @Description:
 */
public interface BlogArticleTagMapper extends BaseMapper<BlogArticleTag> {

    /**
     * 批量插入文章标签
     * @param tagIdList 标签id集合
     * @param articleId 文章id
     * @return 返回插入结果
     */
    int insertBatch(@Param("list") List<Integer> tagIdList, @Param("articleId") Integer articleId);
}
