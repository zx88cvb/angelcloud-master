package com.angel.provider.model.domain;

import lombok.Data;

/**
 * 文章表 标签表 关联表
 * @Author: Angel
 * @Date: 2018/10/11.
 * @Description:
 */
@Data
public class BlogArticleTag {

    /**
     * id
     */
    private Integer id;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 标签id
     */
    private Integer tagId;
}
