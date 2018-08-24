package com.angel.provider.model.form;

import com.angel.base.model.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class BlogArticleForm extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 作者
     */
    @Max(value = 100, message = "作者最大字符不超过100")
    private String author;
    /**
     * 文章标题
     */
    @NotNull(message = "文章标题不能为空!")
    @Size(min = 1, max = 100, message = "文章标题字符长度必须在1~100之间")
    private String title;
    /**
     * 文章摘录(描述)
     */
    @NotNull(message = "文章摘录不能为空!")
    @Max(value = 300, message = "文章摘录最大字符不超过300")
    private String excerpt;
    /**
     * 文章内容
     */
    @NotNull(message = "文章内容不能为空!")
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
     * 分类id
     */
    private Integer categoryId;
    /**
     * 浏览量
     */
    private Integer browseCount;
    /**
     * 是否删除(0: 未删除 1: 已删除)
     */
    private Integer isDel;
}
