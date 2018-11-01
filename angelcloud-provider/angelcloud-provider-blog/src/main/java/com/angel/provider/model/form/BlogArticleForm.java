package com.angel.provider.model.form;

import com.angel.base.model.domain.BaseEntity;
import com.angel.provider.model.vo.BlogTagVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    @Size(max = 100, message = "作者最大字符不超过100")
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
    @Size(min = 1, max = 300, message = "文章摘录字符长度必须在1~300之间")
    private String excerpt;
    /**
     * 文章内容
     */
    @NotNull(message = "文章内容不能为空!")
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
    @NotNull(message = "缩略图不能为空!")
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
    @NotNull(message = "分类不能为空!")
    private Integer categoryId;
    /**
     * 浏览量
     */
    private Integer browseCount;
    /**
     * 是否删除(0: 未删除 1: 已删除)
     */
    private Integer isDel;

    /**
     * 外链
     */
    @Size(max = 500, message = "外链最大字符不超过500")
    private String linkUrl;

    /**
     * 来源
     */
    @Size(max = 50, message = "来源最大字符不超过50")
    private String source;

    /**
     * 发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date postTime;

    /**
     * 标签组
     */
    private List<BlogTagVo> tagList;
}
