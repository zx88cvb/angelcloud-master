package com.angel.provider.model.domain;

import com.angel.base.model.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 博客文章表
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
@Data
public class BlogArticle extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 文章状态(0: 私有 1: 开放)
     */
    private Boolean blogStatus;
    /**
     * 评论状态 (0: 关闭 1 开放)
     */
    private Boolean commentStatus;
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
    private Boolean isRecommend;
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

    /**
     * 外链
     */
    private String linkUrl;

    /**
     * 来源
     */
    private String source;

    /**
     * 发布时间
     */
    private Date postTime;

    /**
     * 文章分类
     */
    @TableField(exist = false)
    private BlogCategory blogCategory;

}
