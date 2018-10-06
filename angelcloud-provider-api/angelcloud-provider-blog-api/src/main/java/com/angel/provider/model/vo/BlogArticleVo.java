package com.angel.provider.model.vo;

import com.angel.base.model.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 博客文章表
 * </p>
 *
 * @author Angel
 * @since 2018-08-24
 */
@Data
public class BlogArticleVo extends BaseVo implements Serializable {

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date postTime;
}