package com.angel.provider.model.domain;

import com.angel.base.model.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 博客点赞表
 * </p>
 *
 * @author Angel
 * @since 2019-03-18
 */
@Data
public class BlogPoll extends BaseEntity implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 用户id(保留)
     */
    private Integer userId;

    /**
     * 是否点赞(0:未点赞 1:点赞)
     */
    private Integer isPositive;

}