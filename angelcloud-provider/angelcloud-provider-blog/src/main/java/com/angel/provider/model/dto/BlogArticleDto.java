package com.angel.provider.model.dto;

import com.angel.base.model.dto.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 博客文章表DTO
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
@Data
public class BlogArticleDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 作者
     */
    private String author;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章摘录(描述)
     */
    private String excerpt;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章状态(0: 私有 1: 开放 2 草稿)
     */
    private Integer blogStatus;
    /**
     * 评论状态 (0: 关闭 1 开放)
     */
    private Integer commentStatus;
    /**
     * 缩略图
     */
    private String thumbnail;
    /**
     * 排序
     */
    private Integer orderIndex;
    /**
     * 是否被推荐(0 未推荐 1 推荐)
     */
    private Integer isRecommend;
    /**
     * 发表用户id
     */
    private Integer userId;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 浏览量
     */
    private Integer browseCount;
}
